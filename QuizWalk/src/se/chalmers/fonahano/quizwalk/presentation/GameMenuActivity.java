package se.chalmers.fonahano.quizwalk.presentation;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.database.GameDatabaseManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class GameMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// remove actionbar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_game_menu);
		// Show the Up button in the action bar.

		// Check if Google play service APK is installed
		ensureGooglePlayServicesIsEnabled();

	}

	private void ensureGooglePlayServicesIsEnabled() {
		int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		Log.i("GOOGLE_PLAY_SERVICES_AVAILABLE", String.valueOf(result));
		if (result != ConnectionResult.SUCCESS) {
			GooglePlayServicesUtil.getErrorDialog(result, this, 0).show();
		}
	}

	public void onButtonClick_StartGame(View v) {
		if (GameDatabaseManager.getInstance().getAllQuizWalkGame().size() >= 1) {
			startActivity(new Intent(this, QuizWalkActivity.class));
		} else {
			Toast.makeText(this,
					getResources().getString(R.string.no_quizwalks),
					Toast.LENGTH_LONG).show();
		}
	}

	public void onButtonClick_CreateGame(View v) {
		Intent intent = new Intent(this, EditQuizWalkGameActivity.class);
		intent.setAction(C.Intent.Action.EDIT_NEW_QUIZ_WALK);
		startActivity(intent);
	}

	public void onButtonClick_QuizWalkManager(View v) {
		startActivity(new Intent(this, QuizWalkManagerActivity.class));
	}

	public void onButtonClick_Settings(View v) {
		startActivity(new Intent(this, SettingsActivity.class));
	}

	public void testCompletedQW(View v) {
		startActivity(new Intent(this, CompletedQuizWalkActivity.class));
	}

}
