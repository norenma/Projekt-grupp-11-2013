package se.chalmers.fonahano.quizwalk.activities;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.R.layout;
import se.chalmers.fonahano.quizwalk.R.menu;
import se.chalmers.fonahano.quizwalk.database.GameDatabaseManager;
import se.chalmers.fonahano.quizwalk.database.LocalDatabase;
import se.chalmers.fonahano.quizwalk.model.AndroidUser;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	public void onButtonClick_logout(View v){
		startActivity(new Intent(this, LoginActivity.class));
	}
	
	public void onButtonClick_help(View v){
		startActivity(new Intent(this, CompletedQuizWalkActivity.class));
	}
	
	public void onButtonClick_about(View v){
		startActivity(new Intent(this, CompletedQuizWalkActivity.class));
	}
	
	public void onButtonClick_save(View v){
		GameDatabaseManager.init(this);
		LocalDatabase gdm=GameDatabaseManager.getInstance();
		AndroidUser user=gdm.getUser();
		user.setUserName(((EditText) this.findViewById(R.id.username_tv)).getText().toString());
		user.setPassword(((EditText) this.findViewById(R.id.password_tv)).getText().toString());
		gdm.updateUser(user);
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setMessage("Saved!");
		builder.show();
	}

}
