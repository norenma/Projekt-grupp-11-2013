package se.chalmers.fonahano.quizwalk.activities;

import java.util.List;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.database.GameDatabaseManager;
import se.chalmers.fonahano.quizwalk.database.LocalDatabase;
import se.chalmers.fonahano.quizwalk.interfaces.C;
import se.chalmers.fonahano.quizwalk.interfaces.Image;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.ChallengeLocation;
import se.chalmers.fonahano.quizwalk.model.ChallengeReward;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import se.chalmers.fonahano.quizwalk.model.StateSingleton;
import se.chalmers.fonahano.quizwalk.model.StringAnswer;
import se.chalmers.fonahano.quizwalk.model.StringQuestion;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

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
public class EditQuizWalkGameActivity extends Activity {

	private QuizWalkGame.Builder builder;
	private GoogleMap map;
	private LatLng activeLocation;

	/***
	 * 
	 * If the activity is called from with CreateGame, it should contain a new
	 * Builder, If the purpose is to Edit a QuizWalk, it should populate the map
	 * with old challenges.
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_game);
		

        
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

		// shows where user is now.
		map.setMyLocationEnabled(true);

		map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

			/***
			 * Listener that responds to Longpresses on the map, to add a
			 * Question. When pressed, it shows a fragment, giving the user a
			 * form to create a challenge.
			 * 
			 */
			@Override
			public void onMapLongClick(LatLng arg0) {
				map.addMarker(new MarkerOptions().position(arg0));

				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

				CreateQuestionFragment cqa = new CreateQuestionFragment();
				fragmentTransaction.add(R.id.fragment_container,
					cqa,
					"question");
				fragmentTransaction.commit();

				activeLocation = arg0;

			}
		});

		// Retrieves the included QuizWalk if there is one.
		if (this.getIntent().getAction().equals(C.Intent.Action.EDIT_NEW_QUIZ_WALK)) {
			builder = new QuizWalkGame.Builder();
		} else{
			QuizWalkGame qwg = StateSingleton.INSTANCE.getActiveQuizWalk().get();
			ActivityHelper.populateMap(map,
					qwg, this);
			builder = new QuizWalkGame.Builder(qwg);
		}
	}

	/***
	 * Creates a challenge out of collected data and adds it to the game. Called
	 * when CreateQuestion-button is pushed.
	 * 
	 * @param view
	 */
	public void CreateQuestion(View view) {
		Challenge.Builder build = new Challenge.Builder();

		// Gets question and answers
		build.question(new StringQuestion((((EditText) this.findViewById(R.id.questionText)).getText().toString())));
		build.correctAnswer(((EditText) this.findViewById(R.id.answer1)).getText()
			.toString());
		build.addIncorrectAnswer(new StringAnswer(((EditText) this.findViewById(R.id.answer2)).getText()
			.toString()));
		build.addIncorrectAnswer(new StringAnswer(((EditText) this.findViewById(R.id.answer3)).getText()
			.toString()));
		build.addIncorrectAnswer(new StringAnswer(((EditText) this.findViewById(R.id.answer4)).getText()
			.toString()));
		build.challengeReward(new ChallengeReward(100,
			" ",
			Optional.<Image> absent()));

		// Gets the coordinates
		ChallengeLocation location = new ChallengeLocation(activeLocation.latitude,
			activeLocation.longitude,
			" ",
			Optional.<Image> absent());
		build.location(location);

		// Builds the challenge
		Challenge c = build.build();

		// Adds question to quizwalk
		this.builder.addChallenge(c);

		// Gets back to the map-view
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

		fragmentTransaction.hide(fragmentManager.findFragmentByTag("question"));
		fragmentTransaction.commit();

	}

	public void CreateQuiz(View view) {
		GameDatabaseManager.init(this);
		LocalDatabase gdm = GameDatabaseManager.getInstance();
		gdm.addQuizWalkGame(builder.build());
	}
}