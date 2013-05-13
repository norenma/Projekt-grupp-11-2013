package se.chalmers.fonahano.quizwalk.activities;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.map.Location;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.ChallengeReward;
import se.chalmers.fonahano.quizwalk.model.Image;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame.Builder;
import se.chalmers.fonahano.quizwalk.model.StringAnswer;
import se.chalmers.fonahano.quizwalk.model.StringQuestion;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.common.base.Optional;


/***
 * Class to show a map and let user insert questions into a custom
 * quizwalk-game. 
 * 
 * @author Markus
 *
 */
public class CreateGameActivity extends Activity {
	
	private QuizWalkGame.Builder builder=new Builder();
	private GoogleMap map;
	private LatLng activeLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_game);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		
		//shows where user is now. 
		map.setMyLocationEnabled(true);
		
		
		
		map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
			
			@Override
			public void onMapLongClick(LatLng arg0) {
				map.addMarker(new MarkerOptions().position(arg0));

				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				
				CreateQuestionFragment cqa=new CreateQuestionFragment();
				fragmentTransaction.add(R.id.fragment_container, cqa, "question");
				fragmentTransaction.commit();
				
				activeLocation=arg0;
				
			}
		});
	}
	
	
	/***
	 * Creates a challenge out of collected data
	 * and adds it to the game. 
	 * Called when CreateQuestion-button is pushed. 
	 * 
	 * @param view
	 */
	public void CreateQuestion(View view){
		Challenge.Builder build=new Challenge.Builder();
		
		//Gets question and answers
		build.question(new StringQuestion((((EditText) this.findViewById(R.id.questionText)).getText().toString())));
		build.correctAnswer(((EditText) this.findViewById(R.id.answer1)).getText().toString());
		build.addIncorrectAnswer(new StringAnswer(((EditText) this.findViewById(R.id.answer2)).getText().toString()));
		build.addIncorrectAnswer(new StringAnswer(((EditText) this.findViewById(R.id.answer3)).getText().toString()));
		build.addIncorrectAnswer(new StringAnswer(((EditText) this.findViewById(R.id.answer4)).getText().toString()));
		build.challengeReward(new ChallengeReward(100, " ", Optional.<Image>absent()));
	
		//Gets the coordinates
		Location location=new Location(activeLocation.latitude, activeLocation.longitude, " ", Optional.<Image>absent());
		build.addLocation(location);
		
		
		//Builds the challenge
		Challenge c=build.build();
		
		//Adds question to quizwalk
		this.builder.addChallenge(c);
		
		//Gets back to the map-view
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		fragmentTransaction.hide(fragmentManager.findFragmentByTag("question"));
		fragmentTransaction.commit();
		
		
	}
}