package se.chalmers.it12.tda367.vt13.grp11.quizwalk.activities;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.R;
import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class CreateGameActivity extends Activity {
	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_walk_game);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		





	}
}