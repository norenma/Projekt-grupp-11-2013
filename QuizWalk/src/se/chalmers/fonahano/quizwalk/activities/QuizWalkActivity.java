package se.chalmers.fonahano.quizwalk.activities;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Iterator;

import se.chalmers.fonahano.quizwalk.C;
import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.C.Intent.Extra;
import se.chalmers.fonahano.quizwalk.database.GameDatabaseManager;
import se.chalmers.fonahano.quizwalk.database.LocalDatabase;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.ChallengeLocation;
import se.chalmers.fonahano.quizwalk.model.Coordinates;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame.ChallengeState;
import se.chalmers.fonahano.quizwalk.model.StateSingleton;
import se.chalmers.fonahano.quizwalk.model.Utilities;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;

public class QuizWalkActivity extends Activity implements LocationListener {
	private GoogleMap map;
	private LocationManager locationManager;
	private String provider;
	private Location location;
	private LocalDatabase db;
	private QuizWalkGame q;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// remove actionbar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_walk_game);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		// db init
		db = GameDatabaseManager.getInstance();

		// shows where user is now.
		map.setMyLocationEnabled(true);

		// Gets a question from test-class, good for now.
		// final QuizWalkGame q =
		// DebugFactory.getRandomTortalChalmersQuizWalkGame1();

		// Checks if the gps is turned on
		LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
		if (!service.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			showEnableGPSDialog();
		}

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		final int gameMapState = getIntent().getIntExtra(
				Extra.GameMap.MAP_STATE, 1);

		if (gameMapState == 1) {
			ActivityHelper.populateMap(map, db.getAllQuizWalkGame(), this);
		} else if (gameMapState == 2) {
			q = StateSingleton.INSTANCE.getActiveQuizWalk().get();
			ActivityHelper.populateMap(map, q, this);
			initProximityAlerts(q, locationManager);
			LatLngBounds.Builder builder = new LatLngBounds.Builder();
			for (Challenge c : q.getChallenges()) {
				builder.include(Utilities.coordinatesToLatLng(c.getLocation()));
			}
			// TODO: Make bounds work
			// LatLngBounds bounds = builder.build();
			// map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds,
			// 100));
		}

		final QuestionDialogBuilder questionFragment = new QuestionDialogBuilder(
				this);

		// Handles if the activity has been prompted by a "questionintent" which
		// has been fired by the proximity alert.
		Intent intent = getIntent();

		if (intent.getBooleanExtra(Extra.PROXIMITY_ALERT_MESSAGE, false) == true) {
			double[] intentChallengeLatLng = intent
					.getDoubleArrayExtra(Extra.PROXIMITY_ALERT_MESSAGE);
			questionFragment.showChallenge(q.getChallenge(new Coordinates(
					intentChallengeLatLng[0], intentChallengeLatLng[1])));
		}

		map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
			@Override
			public boolean onMarkerClick(Marker marker) {
				if (gameMapState == 2) {
					Challenge markerChallenge = q.getChallenge(Utilities
							.latLngToCoordinates(marker.getPosition()));

					if (q.getChallengeStateOf(markerChallenge).equals(
							ChallengeState.COMPLETED)
							|| q.getChallengeStateOf(markerChallenge).equals(
									ChallengeState.FAILED)) {

						Toast.makeText(QuizWalkActivity.this,
								"You already anwsered this question :(",
								Toast.LENGTH_SHORT).show();

					} else {
						questionFragment.showChallenge(q.getChallenge(Utilities
								.latLngToCoordinates(marker.getPosition())));
					}

					if (q.isGameCompleted()) {
						Intent completedQuizWalkIntent = new Intent(
								QuizWalkActivity.this,
								CompletedQuizWalkActivity.class);
						StateSingleton.INSTANCE.setActiveQuizWalk(q);
						completedQuizWalkIntent
								.setAction(C.Intent.Action.STATE_CHANGED_COMPLETED_QUIZWALK);

						startActivity(completedQuizWalkIntent);
					}

				} else {
					showQuizWalkStartDialog(ActivityHelper.getQuizWalkGame(
							marker.getTitle()).get());
				}
				return true;
			}

		});

		checkNotNull(locationManager.getBestProvider(new Criteria(), false));
		provider = locationManager.getBestProvider(new Criteria(), false);

		location = locationManager.getLastKnownLocation(provider);

		// not rendering properly
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(
				new LatLng(location.getLatitude(), location.getLongitude()), 1));

		checkNotNull(location);
		onLocationChanged(location);

	}

	/* Request updates at startup */
	@Override
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(provider, 400, 1, this);
	}

	/* Remove the locationlistener updates when Activity is paused */
	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {
		Log.v("Debug", location.getLongitude() + " " + location.getLatitude());
		// map.moveCamera(CameraUpdateFactory.newLatLngZoom(new
		// LatLng(location.getLatitude(),location.getLongitude()),3));
	}

	@Override
	public void onProviderDisabled(String provider) {
		showEnableGPSDialog();
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	/**
	 * Displays a popup prompting the user to enable the gps and then, if the
	 * user choses to, takes the user to the android settings interface
	 */
	public void showEnableGPSDialog() {

		AlertDialog.Builder ab = new AlertDialog.Builder(this);
		String[] choice = { "Enable GPS", "Exit" };
		ab.setItems(choice, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface d, int choice) {
				if (choice == 0) {
					Intent intent = new Intent(
							Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					startActivity(intent);
				}

			}
		});

		ab.setTitle("Your GPS is disabled, it must be enabled to play to game");

		ab.show();
	}

	public void showQuizWalkStartDialog(final QuizWalkGame q) {
		AlertDialog.Builder ab = new AlertDialog.Builder(this);
		String[] choice = { "Yes", "No" };
		ab.setItems(choice, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface d, int choice) {
				if (choice == 0) {
					Intent intent = new Intent(QuizWalkActivity.this,
							QuizWalkActivity.class);
					intent.putExtra(Extra.GameMap.MAP_STATE, 2);
					StateSingleton.INSTANCE.setActiveQuizWalk(q);

					startActivity(intent);
				}

			}
		});

		ab.setTitle(q.getName() + ": Do you want to play this QuizWalk?");

		ab.show();
	}

	/**
	 * Initiates all the proximity alerts for the QuizWalkGame
	 * 
	 * @param q
	 *            Extracts locations from the quizwalk
	 * @param lm
	 *            Adds proximity alerts to the LocationManager
	 */

	// TODO: Not sure how referencing a LocationManager works. Will need testing
	// TODO: Reformat this method. The way it looks makes my brain scream in
	// agony and pain
	private void initProximityAlerts(QuizWalkGame q, LocationManager lm) {
		Iterator<Challenge> it = q.getChallenges().iterator();
		while (it.hasNext()) {

			Challenge itNext = it.next();
			ChallengeLocation location = itNext.getLocation();
			Intent intent = new Intent(this, QuizWalkActivity.class);
			double[] latNLng = { itNext.getLocation().getLatitude(),
					itNext.getLocation().getLongitude() };

			intent.putExtra(Extra.PROXIMITY_ALERT_MESSAGE, latNLng);
			// To verify that the intent is infact a question
			intent.putExtra(Extra.PROXIMITY_ALERT_MESSAGE, true);

			PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, intent,
					0);
			lm.addProximityAlert(location.getLatitude(),
					location.getLongitude(), Extra.MARKER_PROXIMITY_RADIUS, -1,
					pIntent);
		}
	}
}
