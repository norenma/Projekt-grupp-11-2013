package se.chalmers.fonahano.quizwalk.presentation;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/***
 * A class to summary the result of a players QuizWalkGame.
 */
public class CompletedQuizWalkActivity extends Activity {

	QuizWalkGame quizWalkGame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_completed_quiz_walk);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		if (getIntent().getAction().equals(
				C.Intent.Action.STATE_CHANGED_COMPLETED_QUIZWALK)) {
			quizWalkGame = StateSingleton.INSTANCE.getActiveQuizWalk().get();
			TextView points = (TextView) findViewById(R.id.points);
			points.setText(quizWalkGame.getCurrentScore() + "");
			fillListView(quizWalkGame);

		}

	}

	/**
	 * Fill the ListView with all of the challenges in the current QuizWalkGame.
	 */

	private void fillListView(QuizWalkGame game) {
		List<Challenge> challenges = game.getChallenges();
		ListView list = (ListView) findViewById(R.id.challenges);
		List<String> items = new ArrayList<String>();
		for (Challenge c : challenges) {
			items.add(c.getQuestion().toString());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);
		list.setAdapter(adapter);
	}
}
