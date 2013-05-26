package se.chalmers.fonahano.quizwalk.activities;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.interfaces.C;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import se.chalmers.fonahano.quizwalk.model.StateSingleton;
import temp.debug.tortal.DebugFactory;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CompletedQuizWalkActivity extends Activity {

	QuizWalkGame quizWalkGame;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if(getIntent().getAction().equals(C.Intent.Action.STATE_CHANGED_COMPLETED_QUIZWALK)){
			quizWalkGame = StateSingleton.INSTANCE.getActiveQuizWalk().get();
		}
		
		// remove actionbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_completed_quiz_walk);
		
		TextView points=(TextView) findViewById(R.id.points);
		points.setText(quizWalkGame.getCurrentScore() +"");
		fillListView(quizWalkGame);
	}
	
	public void fillListView(QuizWalkGame game){
		List<Challenge> challenges = game.getChallenges();
		ListView list = (ListView) findViewById(R.id.challenges);
		List<String> items = new ArrayList<String>();
		for(Challenge c: challenges){
			items.add(c.getQuestion().toString());
		}
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
		list.setAdapter(adapter);
	}
}
