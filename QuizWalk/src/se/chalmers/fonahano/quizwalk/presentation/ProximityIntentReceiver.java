package se.chalmers.fonahano.quizwalk.presentation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 *
 */
public class ProximityIntentReceiver extends BroadcastReceiver {

	private static final String THE_PROXIMITY_ALERT_WORKS = "The proximity alert works!";

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, THE_PROXIMITY_ALERT_WORKS, Toast.LENGTH_LONG)
				.show();
		Intent backToQuizWalk = new Intent(context, QuizWalkActivity.class);
		backToQuizWalk.putExtras(intent);
		context.startActivity(backToQuizWalk);
	}

}
