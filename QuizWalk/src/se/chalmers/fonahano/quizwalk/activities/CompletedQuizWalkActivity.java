package se.chalmers.fonahano.quizwalk.activities;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import temp.debug.tortal.DebugFactory;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class CompletedQuizWalkActivity extends Activity {

	QuizWalkGame quizWalkGame = DebugFactory.getRandomTortalChalmersQuizWalkGame1();
	
	//TODO get Quizwalks from Singleton
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_completed_quiz_walk);
		
		TextView points=(TextView) findViewById(R.id.points);
		points.setText(quizWalkGame.getReward().get().getScore() + "");
	}
	
	

}
