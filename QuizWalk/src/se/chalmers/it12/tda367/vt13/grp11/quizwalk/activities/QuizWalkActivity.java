package se.chalmers.it12.tda367.vt13.grp11.quizwalk.activities;

import java.util.Iterator;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.R;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Challenge;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.QuizWalkGame;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.utils.Utilities;
import temp.debug.norenma.TestRun;
import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class QuizWalkActivity extends Activity {
	private GoogleMap map;

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


	}
}
