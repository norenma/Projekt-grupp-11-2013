package se.chalmers.fonahano.quizwalk.activities;

import se.chalmers.fonahano.quizwalk.activities.QuizWalkActivity;
import se.chalmers.fonahano.quizwalk.interfaces.C;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;


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
		Intent backToQuizWalk = new Intent(context, QuizWalkActivity.class);
		backToQuizWalk.putExtras(intent);
		context.startActivity(backToQuizWalk);
	}

}
