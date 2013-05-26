package se.chalmers.fonahano.quizwalk.presentation;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.R.layout;
import se.chalmers.fonahano.quizwalk.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

/***
 * A screen that tells a little bit about QuizWalk. 
 * 
 * @author Johanna
 */

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
	}
	
	public void onButtonClick_BackToSettings(View v){
		startActivity(new Intent(this, SettingsActivity.class));
	}

	
}
