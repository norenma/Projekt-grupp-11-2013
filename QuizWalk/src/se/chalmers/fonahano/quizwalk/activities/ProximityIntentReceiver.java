package se.chalmers.fonahano.quizwalk.activities;

import se.chalmers.fonahano.quizwalk.activities.QuizWalkActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


/**
 * 
 */

/**
 * @author HForsvall
 *
 */
public class ProximityIntentReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "The proximity alert works!", Toast.LENGTH_LONG).show();
		Intent backToQuizWalk = new Intent(context, QuizWalkActivity.class);
		backToQuizWalk.putExtras(intent);
		context.startActivity(backToQuizWalk);
	}

}
