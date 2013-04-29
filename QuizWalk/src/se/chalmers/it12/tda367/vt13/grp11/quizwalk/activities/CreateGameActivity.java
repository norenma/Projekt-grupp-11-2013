package se.chalmers.it12.tda367.vt13.grp11.quizwalk.activities;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

public class CreateGameActivity extends Activity {
	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_walk_game);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		
		//shows where user is now. 
		map.setMyLocationEnabled(true);
		
		map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
			
			@Override
			public void onMapLongClick(LatLng arg0) {
				startActivity(new Intent(CreateGameActivity.this, CreateQuestionActivity.class));
				
				
			}
		});



	}
}