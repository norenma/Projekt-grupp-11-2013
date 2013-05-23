package se.chalmers.fonahano.quizwalk.activities;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.database.GameDatabaseManager;
import se.chalmers.fonahano.quizwalk.database.LocalDatabase;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_login);
		GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		map.getUiSettings().setAllGesturesEnabled(false);
		
		map.getUiSettings().setZoomControlsEnabled(false);
		
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(57.685528, 11.979389), 12));

		map.getUiSettings().setZoomControlsEnabled(false);
		
		CameraPosition cameraPos = new CameraPosition.Builder().target(new LatLng(57.685528, 12.979389)).zoom(12).build();
		
		map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPos), 100000, null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
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
