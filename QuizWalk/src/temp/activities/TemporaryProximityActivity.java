package temp.activities;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.activities.QuizWalkActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Temporary activity for proximity alert testing purposes
 * @author Hampus Forsvall, forzvall@gmail.com
 *
 */

public class TemporaryProximityActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temporary_proximity);
		// Show the Up button in the action bar.
		setupActionBar();
		Intent intent = getIntent();
		float[] f = intent.getFloatArrayExtra(QuizWalkActivity.PROXIMITY_ALERT_MESSAGE);
		
		TextView latitudeTV = (TextView) findViewById(R.id.debug_latitude);
		TextView longitudeTV = (TextView) findViewById(R.id.debug_longitude);
		
		latitudeTV.setText(String.valueOf(f[0]));
		longitudeTV.setText(String.valueOf(f[1]));
		
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.temporary_proximity, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
