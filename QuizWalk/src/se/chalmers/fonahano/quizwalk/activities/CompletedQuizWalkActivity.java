package se.chalmers.fonahano.quizwalk.activities;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.R.layout;
import se.chalmers.fonahano.quizwalk.R.menu;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame.ChallengeState;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGameReward;
import temp.debug.tortal.DebugFactory;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class CompletedQuizWalkActivity extends Activity {

	QuizWalkGame quizWalkGame = DebugFactory.getRandomTortalChalmersQuizWalkGame1();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_completed_quiz_walk);
		
		TextView points=(TextView) findViewById(R.id.points);
		points.setText(quizWalkGame.getReward().get().getScore() + "");
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.completed_quiz_walk, menu);
		return true;
	}
	
	//Metoden tar en QuizWalkGame som beräknar och returnerar poängen på alla challenges. 
	public int calculatePoints(QuizWalkGame game){
		int points = 0;
		
		for(Challenge c: game.getChallenges()){
			quizWalkGame.setChallengeState(c, ChallengeState.COMPLETED);
			if(game.getChallengeStateOf(c) == ChallengeState.COMPLETED){
				points+= game.getReward().get().getScore();
			}
		}
		return points; 
	}
	

}
