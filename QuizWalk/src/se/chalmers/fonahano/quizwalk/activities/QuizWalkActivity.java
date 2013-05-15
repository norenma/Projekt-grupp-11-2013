package se.chalmers.fonahano.quizwalk.activities;

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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.map.ChallengeLocation;
import se.chalmers.fonahano.quizwalk.map.Coordinates;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import se.chalmers.fonahano.quizwalk.utils.C;
import se.chalmers.fonahano.quizwalk.utils.Utilities;
import temp.debug.norenma.TestRun;

import java.util.Iterator;

import static com.google.common.base.Preconditions.checkNotNull;


public class QuizWalkActivity extends Activity implements LocationListener {
	private GoogleMap map;
	private LocationManager locationManager;
	private String provider;
	private Location location;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_walk_game);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		
		//shows where user is now. 
		map.setMyLocationEnabled(true);

		// Gets a question from test-class, good for now.
		final QuizWalkGame q = TestRun.createGame();
		Iterator<Challenge> challengeIt = q.getChallenges().iterator();

		// Sets out locations on map
		Utilities.populateMap(map, q);

		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(53.558,
				9.927), 3));

		final QuestionDialogBuilder questionFragment = new QuestionDialogBuilder(
				this);

        //Handles if the activity has been prompted by a "questionintent" which has been fired by the proximity alert.
        Intent intent = getIntent();

        if(intent.getBooleanExtra(C.PROXIMITY_ALERT_MESSAGE, false) == true) {
            double[] intentChallengeLatLng = intent.getDoubleArrayExtra(C.PROXIMITY_ALERT_MESSAGE);
            questionFragment.showChallenge(q.getChallenge(new Coordinates(intentChallengeLatLng[0], intentChallengeLatLng[1])));
        }



		map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
			@Override
            public boolean onMarkerClick(Marker arg0) {
				/*for (int i = 0; i < q.getChallenges().size(); i++) {
					if (arg0.getTitle().equals(
							q.getChallenges().get(i).getChallengeDescription())) {
						questionFragment
								.showChallenge(q.getChallenges().get(i));

                        arg0.getPosition();
					}
				}
				return false;*/

                questionFragment.showChallenge(q.getChallenge(Utilities.latLngToCoordinates(arg0.getPosition())));
                return true;
			}

            });

            //Checks if the gps is turned on
		LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
		if(!service.isProviderEnabled(LocationManager.GPS_PROVIDER)){
			showEnableGPSDialog();
		}
		
	    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    checkNotNull(locationManager.getBestProvider(new Criteria(), false));
	    provider = locationManager.getBestProvider(new Criteria(), false);

	    location = locationManager.getLastKnownLocation(provider);
		
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
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),location.getLongitude()),3));	
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
	 * Displays a popup prompting the user to enable the gps and then, if the user choses to, takes the user to the android settings interface
	 */
	public void showEnableGPSDialog() {
		
		AlertDialog.Builder ab = new AlertDialog.Builder(this);
		String[] choice = {"Enable GPS", "Exit"};
		ab.setItems(choice, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface d, int choice) {
				if(choice == 0){
					Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					startActivity(intent);
				}
				
			}
		});
		
		ab.setTitle("Your GPS is disabled, it must be enabled to play to game");

		ab.show();
	}
	/**
	 * Initiates all the proximity alerts for the QuizWalkGame
	 * @param q
	 * 		Extracts locations from the quizwalk
	 * @param lm
	 * 		Adds proximity alerts to the LocationManager
	 */
	
	//TODO: Not sure how referencing a LocationManager works. Will need testing
	//TODO: Reformat this method. The way it looks makes my brain scream in agony and pain
	private void initProximityAlerts(QuizWalkGame q, LocationManager lm){
		Iterator<Challenge> it = q.getChallenges().iterator();
		while(it.hasNext()){
			//Iterator<se.chalmers.fonahano.quizwalk.map.ChallengeLocation> locIt = it.next().getListOfLocations().iterator();
			//while(locIt.hasNext()){
                Challenge itNext = it.next();
				ChallengeLocation location = itNext.getLocation();
				Intent intent = new Intent(this, QuizWalkActivity.class);
                double[] latNLng = {itNext.getLocation().getLatitude(),itNext.getLocation().getLongitude()};

				intent.putExtra(C.PROXIMITY_ALERT_MESSAGE, latNLng);
                //To verify that the intent is infact a question
                intent.putExtra(C.PROXIMITY_ALERT_MESSAGE, true);

				PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
				lm.addProximityAlert(location.getLatitude(), location.getLongitude(), C.MARKER_PROXIMITY_RADIUS, -1, pIntent);
			//}
		}
	}
}
