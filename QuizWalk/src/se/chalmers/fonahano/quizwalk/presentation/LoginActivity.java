package se.chalmers.fonahano.quizwalk.presentation;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.database.GameDatabaseManager;
import se.chalmers.fonahano.quizwalk.database.LocalDatabase;
import android.app.Activity;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// remove actionbar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_login);
		GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.map)).getMap();
		map.getUiSettings().setAllGesturesEnabled(false);

		map.getUiSettings().setZoomControlsEnabled(false);

		// checks if gps is turned on
		if (!((LocationManager) getSystemService(LOCATION_SERVICE))
				.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			ActivityHelper.showEnableGPSDialog(this);
		} else {
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
					57.685528, 11.979389), 12));
		}

		map.getUiSettings().setZoomControlsEnabled(false);

	}

	/***
	 * Checks so that stated mail and password is correct
	 * 
	 * @param v
	 */
	public void Login(View v) {
		GameDatabaseManager.init(this);
		LocalDatabase gdm = GameDatabaseManager.getInstance();
		if (gdm.getUser().isCorrectPassword(
				((EditText) findViewById(R.id.Password)).getText().toString())
				&& gdm.getUser()
						.getEmail()
						.equals(((EditText) findViewById(R.id.Email)).getText()
								.toString())) {
			Intent intent = new Intent(this, GameMenuActivity.class);
			startActivity(intent);
		} else {
			// shows a error-text
			((TextView) findViewById(R.id.errorMessage))
					.setText(R.string.wrong_password);
		}

	}

	public void onClickRegisterUser(View view) {
		Intent intent = new Intent(this, RegisterUserActivity.class);
		startActivity(intent);
	}

}
