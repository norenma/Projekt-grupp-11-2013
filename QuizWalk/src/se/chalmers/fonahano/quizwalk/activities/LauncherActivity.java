package se.chalmers.fonahano.quizwalk.activities;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.database.GameDatabaseManager;
import se.chalmers.fonahano.quizwalk.database.LocalDatabase;
import se.chalmers.fonahano.quizwalk.interfaces.C;
import se.chalmers.fonahano.quizwalk.model.AndroidUser;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

/***
 * Empty activity to set up program before visual things will happen.
 * 
 * @author Markus
 *
 */
public class LauncherActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Debug, delete database.
		deleteDatabase(C.Data.DATABASE_NAME);
		GameDatabaseManager.init(this);

		LocalDatabase gdm = GameDatabaseManager.getInstance();
		AndroidUser user = gdm.getUser();
		if (user == null) {
			gdm.createUser(new AndroidUser());
		}
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);
		return true;
	}

}
