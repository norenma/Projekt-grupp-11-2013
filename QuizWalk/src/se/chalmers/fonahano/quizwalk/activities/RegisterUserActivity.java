package se.chalmers.fonahano.quizwalk.activities;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.database.GameDatabaseManager;
import se.chalmers.fonahano.quizwalk.database.LocalDatabase;
import se.chalmers.fonahano.quizwalk.model.AndroidUser;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/***
 * Class to register a user.
 * 
 * @author Markus
 * 
 */
public class RegisterUserActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_user);
		// Show the Up button in the action bar.
		setupActionBar();
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
		// getMenuInflater().inflate(R.menu.register_user, menu);
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
	
	/***
	 * When clicked, registers the user to local database, and confirms this with a dialog to the user.
	 * User will be sent to login again after this. 
	 * @param view
	 */
	public void OnClickRegister(View view){
		GameDatabaseManager.init(this);
		LocalDatabase gdm=GameDatabaseManager.getInstance();
		AndroidUser user=gdm.getUser();
		user.setEmail(((EditText) this.findViewById(R.id.PasswordText)).getText().toString());
		user.setPassword(((EditText) this.findViewById(R.id.PasswordText)).getText().toString());
		gdm.updateUser(user);
		
		//Shows a fragment after registration 
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle(R.string.registration_successful).setCancelable(false)
	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	                Intent intent=new Intent(RegisterUserActivity.this, LoginActivity.class);
	                startActivity(intent);
	           }
	       });
		builder.create().show();
	}


}
