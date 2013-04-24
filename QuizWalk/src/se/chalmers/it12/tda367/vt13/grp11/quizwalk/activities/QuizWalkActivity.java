package se.chalmers.it12.tda367.vt13.grp11.quizwalk.activities;

import java.util.Iterator;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.R;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Challenge;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.QuizWalkGame;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.utils.Utilities;
import temp.debug.norenma.TestRun;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class QuizWalkActivity extends Activity {
  private GoogleMap map;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_quiz_walk_game);
    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
        .getMap();
    
    QuizWalkGame q=TestRun.createGame();
    Utilities.populateMap(map, q);
    
    AlertDialog.Builder ab=new AlertDialog.Builder(this);
    
    Iterator<Challenge> i =q.getChallenges().iterator();
    Challenge c=i.next();
    
    ab.setTitle(c.getChallengeDescription());
    ab.show();
    

    
  } 
  
}