package se.chalmers.fonahano.quizwalk.activities;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.map.Location;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.ChallengeReward;
import se.chalmers.fonahano.quizwalk.model.Image;
import se.chalmers.fonahano.quizwalk.model.StringAnswer;
import se.chalmers.fonahano.quizwalk.model.StringQuestion;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.common.base.Optional;




public class CreateQuestionActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_question);

		
		
	}
	
	public void CreateQuestion(View view){
		Challenge.Builder build=new Challenge.Builder();
		
		//Gets question and answers
		build.question(new StringQuestion((((EditText) this.findViewById(R.id.questionText)).getText().toString())));
		build.correctAnswer(((EditText) this.findViewById(R.id.answer1)).getText().toString());
		build.addIncorrectAnswer(new StringAnswer(((EditText) this.findViewById(R.id.answer2)).getText().toString()));
		build.addIncorrectAnswer(new StringAnswer(((EditText) this.findViewById(R.id.answer3)).getText().toString()));
		build.addIncorrectAnswer(new StringAnswer(((EditText) this.findViewById(R.id.answer4)).getText().toString()));
		build.challengeReward(new ChallengeReward(100, " ", Optional.<Image>absent()));
	
		//Gets the coordinates
		double[] locs=getIntent().getExtras().getDoubleArray(LOCATION_SERVICE);
		build.addLocation(new Location(locs[0],locs[1]," ", Optional.<Image>absent()));
		
		//Builds the challenge
		Challenge c=build.build();
		
		//Gets back to the map-view
		Intent intent=new Intent(this, CreateGameActivity.class);
		intent.putExtra("Challenge", c);
		startActivity(intent);
		
	}

}
