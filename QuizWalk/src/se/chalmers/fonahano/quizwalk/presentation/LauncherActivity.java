package se.chalmers.fonahano.quizwalk.presentation;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.database.GameDatabaseManager;
import se.chalmers.fonahano.quizwalk.database.LocalDatabase;
import se.chalmers.fonahano.quizwalk.interfaces.User;
import se.chalmers.fonahano.quizwalk.model.AndroidUser;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

/**
 * Empty activity to set up program before visual things will happen.
 * 
 * @author Markus
 *
 */
public class LauncherActivity extends Activity {

	
	private final int SPLASH_DISPLAY_LENGHT = 1000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_launcher);
		
		deleteDatabase(C.Data.DATABASE_NAME);
		GameDatabaseManager.init(this);

		LocalDatabase gdm = GameDatabaseManager.getInstance();
		User user = gdm.getUser();
		if (user == null) {
			gdm.createUser(new AndroidUser());
		}

		new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(LauncherActivity.this,LoginActivity.class);
                LauncherActivity.this.startActivity(mainIntent);
                LauncherActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);

	}



}
