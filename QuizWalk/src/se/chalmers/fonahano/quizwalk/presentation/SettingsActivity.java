package se.chalmers.fonahano.quizwalk.presentation;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.database.GameDatabaseManager;
import se.chalmers.fonahano.quizwalk.database.LocalDatabase;
import se.chalmers.fonahano.quizwalk.model.QuizWalkUser;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

/***
 * A settingsscreen that represents all the users different settings.
 * 
 * @author Johanna
 */

public class SettingsActivity extends Activity {

	/**
	 * Creates SettingsActivity and shows the layout. Removes actionbar.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// remove actionbar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
	}

	/**
	 * Takes the user back to the loginscreen.
	 */
	public void onButtonClick_logout(View v) {
		startActivity(new Intent(this, LoginActivity.class));
	}

	public void onButtonClick_about(View v) {
		startActivity(new Intent(this, CompletedQuizWalkActivity.class));
	}

	/**
	 * Give the user possibility to change user.
	 */
	public void onButtonClick_save(View v) {
		GameDatabaseManager.init(this);
		LocalDatabase gdm = GameDatabaseManager.getInstance();
		QuizWalkUser user = gdm.getUser();
		user.setUserName(((EditText) this.findViewById(R.id.username_tv))
				.getText().toString());
		user.setPassword(((EditText) this.findViewById(R.id.password_tv))
				.getText().toString());
		gdm.updateUser(user);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(getResources().getString(R.string.saved));
		builder.show();
	}

}
