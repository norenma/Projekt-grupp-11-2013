package se.chalmers.fonahano.quizwalk.activities;

import static se.chalmers.fonahano.quizwalk.model.Utilities.checkNotNullOrEmpty;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.database.GameDatabaseManager;
import se.chalmers.fonahano.quizwalk.database.LocalDatabase;
import se.chalmers.fonahano.quizwalk.interfaces.C;
import se.chalmers.fonahano.quizwalk.interfaces.Image;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.Challenge.Builder;
import se.chalmers.fonahano.quizwalk.model.ChallengeLocation;
import se.chalmers.fonahano.quizwalk.model.ChallengeReward;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import se.chalmers.fonahano.quizwalk.model.StateSingleton;
import se.chalmers.fonahano.quizwalk.model.StringQuestion;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.common.base.Optional;

/***
 * Class to show a map and let user insert questions into a custom
 * quizwalk-game.
 * 
 * @author Markus Andersson Norén
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
		// remove actionbar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_game);

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		// shows where user is now.
		map.setMyLocationEnabled(true);
		map.getUiSettings().setZoomControlsEnabled(false);

		setupMap();

		// Retrieves the included QuizWalk if there is one.
		if (this.getIntent().getAction()
				.equals(C.Intent.Action.EDIT_NEW_QUIZ_WALK)) {
			builder = new QuizWalkGame.Builder();
		} else {
			QuizWalkGame qwg = StateSingleton.INSTANCE.getActiveQuizWalk()
					.get();
			ActivityHelper.populateMap(map, qwg, this);
			builder = new QuizWalkGame.Builder(qwg);
		}

		// Small text on the screen to let the user know how to input a new
		// question
		// to the game.
		Toast toast = Toast.makeText(this,
				"Longpress a location the add a question", Toast.LENGTH_LONG);
		toast.show();
	}

	/**
	 * Sets up the map, and adds a click-listener
	 */
	private void setupMap() {
		map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

			/***
			 * Listener that responds to Long presses on the map, to add a
			 * Question. When pressed, it shows a fragment, giving the user a
			 * form to create a challenge.
			 * 
			 */
			@Override
			public void onMapLongClick(LatLng arg0) {
				map.addMarker(new MarkerOptions().position(arg0));

				// Creates and shows a dialog asking user for question details
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();

				CreateQuestionFragment cqa = new CreateQuestionFragment();
				fragmentTransaction.add(R.id.fragment_container, cqa,
						"question");
				fragmentTransaction.commit();

				activeLocation = arg0;

			}
		});
	}

	/***
	 * Creates a challenge out of collected data and adds it to the game. Called
	 * when CreateQuestion-button is pushed.
	 * 
	 * @param view
	 */
	public void CreateQuestion(View view) {
		Challenge.Builder build = new Challenge.Builder();

		try {
			// Gets question and answers
			addQuestionsAndAnswers(build);
		} catch (IllegalArgumentException e) {
			//error-message if any empty input
			findViewById(R.id.errorMessage).setAlpha(1);
			e.printStackTrace();
			return;
		}

		// Gets the coordinates
		ChallengeLocation location = new ChallengeLocation(
				activeLocation.latitude, activeLocation.longitude, " ",
				Optional.<Image> absent());
		build.location(location);

		// Builds the challenge
		Challenge c = build.build();

		// Adds question to quizwalk
		this.builder.addChallenge(c);

		// Gets back to the map-view
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		fragmentTransaction.hide(fragmentManager.findFragmentByTag("question"));
		fragmentTransaction.commit();

		// Small text on the screen to let the user know that the Question is
		// created
		Toast toast = Toast.makeText(this, "Question Created!",
				Toast.LENGTH_SHORT);
		toast.show();
	}

	/**
	 * Gets question with answers from input
	 * @param build adds question and answers to this build. 
	 */
	private void addQuestionsAndAnswers(Builder build) throws IllegalArgumentException {
		build.question(new StringQuestion(checkNotNullOrEmpty(((EditText) this
				.findViewById(R.id.questionText)).getText()
				.toString(),"")));
		build.correctAnswer(checkNotNullOrEmpty(((EditText) this
				.findViewById(R.id.answer1)).getText().toString(), ""));
		build.correctAnswer(checkNotNullOrEmpty(((EditText) this
				.findViewById(R.id.answer2)).getText().toString(), ""));
		build.correctAnswer(checkNotNullOrEmpty(((EditText) this
				.findViewById(R.id.answer3)).getText().toString(), ""));
		build.correctAnswer(checkNotNullOrEmpty(((EditText) this
				.findViewById(R.id.answer4)).getText().toString(), ""));
		build.challengeReward(new ChallengeReward(10, " ", Optional
		.<Image> absent()));
		
	}

	/**
	 * Creates a quizwalk, when the user presses a button.
	 * 
	 * @param view
	 */
	public void CreateQuiz(View view) {
		// Asks the User to name the Quiz
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setMessage("Name your quiz:");

		// Set an EditText view to get user input of the quizwalk name 
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Create!",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						String value = input.getText().toString();
						builder.name(value);

						// Builds quizwalk and saves to database
						GameDatabaseManager.init(EditQuizWalkGameActivity.this);
						LocalDatabase gdm = GameDatabaseManager.getInstance();
						gdm.addQuizWalkGame(builder.build());

						// Small text on the screen to let the user know how to
						// input a new
						// question
						// to the game.
						Toast toast = Toast.makeText(
								EditQuizWalkGameActivity.this,
								"QuizWalk created!", Toast.LENGTH_LONG);
						toast.show();
						// Sends user back to the menu. 
						startActivity(new Intent(EditQuizWalkGameActivity.this,
								GameMenuActivity.class));
					}
				});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						return;
					}
				});
		alert.show();
	}
}
