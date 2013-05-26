package se.chalmers.fonahano.quizwalk.presentation;

import se.chalmers.fonahano.quizwalk.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class DebugActivity extends Activity {

	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// remove actionbar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.debug_screen);

		textView = (TextView) findViewById(R.id.debug_text);
		if (getIntent().hasExtra(C.Data.JSON_DATA)) {
			textView.setText(getIntent().getStringExtra(C.Data.JSON_DATA));
		}
	}

}
