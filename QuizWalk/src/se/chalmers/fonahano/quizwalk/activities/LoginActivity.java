package se.chalmers.fonahano.quizwalk.activities;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.database.GameDatabaseManager;
import se.chalmers.fonahano.quizwalk.database.LocalDatabase;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/***
	 * Checks so that stated mail and password is correct
	 * @param v
	 */
	public void Login(View v) {
		GameDatabaseManager.init(this);
		LocalDatabase gdm=GameDatabaseManager.getInstance();
		//if(password correct and mail correct)
		//if(gdm.getUser().isCorrectPassword(((EditText)findViewById(R.id.Password)).getText().toString())
		//		&& gdm.getUser().getEmail().equals(((EditText)findViewById(R.id.Email)).getText().toString())){
			Intent intent = new Intent(this, GameMenuActivity.class);
			startActivity(intent);
		//}
		//else{
			//shows a error-text
		//	((TextView) findViewById(R.id.errorMessage)).setText(R.string.wrong_password);
		//}
		
	}
	
	public void onClickRegisterUser(View view){
		Intent intent = new Intent(this, RegisterUserActivity.class);
		startActivity(intent);
	}

}
