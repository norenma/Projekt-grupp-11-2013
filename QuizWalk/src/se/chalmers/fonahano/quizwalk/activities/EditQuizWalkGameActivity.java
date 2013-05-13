package se.chalmers.fonahano.quizwalk.activities;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.R.layout;
import se.chalmers.fonahano.quizwalk.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class EditQuizWalkGameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_quiz_walk_game);
	}

	@Override
	//INACTIVE
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.edit_quiz_walk_game,
		//	menu);
		//INACTIVE
		return false;
	}

}
