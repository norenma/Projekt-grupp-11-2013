package se.chalmers.fonahano.quizwalk.activities;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.interfaces.C;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import temp.debug.tortal.DebugFactory;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.common.base.Optional;

public class GameMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_menu);
		// Show the Up button in the action bar.
		setupActionBar();
		
		//Check if Google play service APK is installed
		ensureGooglePlayServicesIsEnabled();
		
		//Adds a few random QuizWalks to the db. For debuging
		DebugFactory.addRandomListOfQuizWalksToDB();
		
	}
	
	private void ensureGooglePlayServicesIsEnabled() {
		int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		Log.i("GOOGLE_PLAY_SERVICES_AVAILABLE", String.valueOf(result));
		if (result != ConnectionResult.SUCCESS) {
			GooglePlayServicesUtil.getErrorDialog(result, this, 0).show();
		}
	}

	public void onButtonClick_StartGame(View v) {
		startActivity(new Intent(this, QuizWalkActivity.class));
	}
	
	public void onButtonClick_CreateGame(View v) {
		Intent intent=new Intent(this, EditQuizWalkGameActivity.class);
		intent.setAction(C.Intent.Action.EDIT_NEW_QUIZ_WALK);
		startActivity(intent);
	}
	public void onButtonClick_QuizWalkManager(View v) {
		startActivity(new Intent(this, QuizWalkManagerActivity.class));
	}
	public void onButtonClick_Settings(View v){
		startActivity(new Intent(this, SettingsActivity.class));
	}
	public void testCompletedQW(View v){
		startActivity(new Intent(this, CompletedQuizWalkActivity.class));
	}
	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.second_screen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	

}
