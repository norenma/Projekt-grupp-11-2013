package se.chalmers.fonahano.quizwalk.activities;

import se.chalmers.fonahano.quizwalk.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class DebugActivity extends Activity {

	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.debug_screen);

		textView = (TextView) findViewById(R.id.debug_text);
		if (getIntent().hasExtra("se.chalmers.fonahano.quizwalk.json_data")) {
			textView.setText(getIntent().getStringExtra("se.chalmers.fonahano.quizwalk.json_data"));
		}
	}
	
	@Override
	//INACTIVE
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.edit_quiz_walk_game,
		//	menu);
		//INACTIVE
		return false;
	}

}
