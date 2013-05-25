package se.chalmers.fonahano.quizwalk.activities;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.interfaces.C;
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
		if (getIntent().hasExtra(C.Data.JSON_DATA)) {
			textView.setText(getIntent().getStringExtra(C.Data.JSON_DATA));
		}
	}
	
	@Override
	//INACTIVE
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

}
