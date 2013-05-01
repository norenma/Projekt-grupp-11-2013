package se.chalmers.it12.tda367.vt13.grp11.quizwalk.activities;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Iterator;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.R;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Challenge;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.QuizWalkGame;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.utils.Utilities;
import temp.debug.norenma.TestRun;
import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

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

		map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
			@Override
			public boolean onMarkerClick(Marker arg0) {
				for (int i = 0; i < q.getChallenges().size(); i++) {
					if (arg0.getTitle().equals(
							q.getChallenges().get(i).getChallengeDescription())) {
						questionFragment
								.showChallenge(q.getChallenges().get(i));
					}
				}
				return false;
			}

		});
		
		
	    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
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

	@Override
	public void onLocationChanged(Location location) {
		Log.v("Debugg", location.getLongitude() + " " + location.getLatitude());
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),location.getLongitude()),3));	
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}
