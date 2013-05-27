package se.chalmers.fonahano.quizwalk.presentation;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.database.GameDatabaseManager;
import se.chalmers.fonahano.quizwalk.database.LocalDatabase;
import se.chalmers.fonahano.quizwalk.interfaces.User;
import se.chalmers.fonahano.quizwalk.model.QuizWalkUser;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Empty activity to set up program before GUI.
 * 
 */
public class LauncherActivity extends Activity {

	private static final int SPLASH_DISPLAY_LENGHT = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		// remove title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_launcher);

		// deleteDatabase(C.Data.DATABASE_NAME);
		GameDatabaseManager.init(this);

		LocalDatabase gdm = GameDatabaseManager.getInstance();
		User user = gdm.getUser();
		if (user == null) {
			gdm.createUser(new QuizWalkUser());
		}

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				/* Create an Intent that will start the Menu-Activity. */
				Intent mainIntent = new Intent(LauncherActivity.this,
						LoginActivity.class);
				LauncherActivity.this.startActivity(mainIntent);
				LauncherActivity.this.finish();
			}
		}, SPLASH_DISPLAY_LENGHT);

	}

}
