package se.chalmers.fonahano.quizwalk.activities.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.map.Coordinates;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;

/**
 * Handles simple, android related, utilies. Much like the model util class only compatible with the google api.
 * @author Hampus Forsvall, forzvall@gmail.com
 *
 */

//TODO: Not sure if this is the proper way of handling this, feel free to interfer(spelling?)
public class UtilitesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_utilites);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.utilites, menu);
		return true;
	}
	
	/**
	 * Calculates the distance between two points;
	 * @param firstPos
	 * @param secondPos
	 * @param result
	 * 		an array containing the result
	 */
	public static void distance(Coordinates firstPos, Coordinates secondPos, float[] result){
		Location.distanceBetween(firstPos.getLatitude(), firstPos.getLongitude(), secondPos.getLatitude(), secondPos.getLongitude(), result);
	}
	
	public static double distance(Coordinates firstPos, Coordinates secondPos){
		float[] r = new float[1];
		distance(firstPos, secondPos, r);
		return r[0];
	}
	
	public static List<Double> distance(QuizWalkGame q){
		List<Double> l = new ArrayList<Double>();
		Iterator<Challenge> it = q.getChallenges().iterator();
		Coordinates f, s;
		Challenge itNext = it.hasNext() ? it.next():null;
		
		while(true){
			if(it.hasNext()){
				f = itNext.getLocation();
			}else{
				break;
			}
			
			if(it.hasNext()){
				itNext = it.next();
				s = itNext.getLocation();
			}else{
				break;
			}
			
			l.add(distance(f, s));
		}
		
		
		return l;
	}

}
