package se.chalmers.fonahano.quizwalk.activities;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;

import se.chalmers.fonahano.quizwalk.interfaces.LatitudeLongitude;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.ChallengeLocation;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import se.chalmers.fonahano.quizwalk.model.Utilities;
import android.location.Location;

/**
 * Every GUI or activity related convenience methods should be here!
 * 
 */
public abstract class ActivityHelper {

	//TODO: Behöver denne vara public?
	static void distance(LatitudeLongitude firstPos,
			LatitudeLongitude secondPos, float[] result) {
		Location.distanceBetween(firstPos.getLatitude(),
			firstPos.getLongitude(),
			secondPos.getLatitude(),
			secondPos.getLongitude(),
			result);
	}

	public static float distance(LatitudeLongitude firstPos,
			LatitudeLongitude secondPos) {
		float[] floatArray = new float[1];
		distance(firstPos,
			secondPos,
			floatArray);
		return floatArray[0];
	}

	//TODO: varför returnerar denna en list of tal, istället för summan?
	public static List<Float> distance(QuizWalkGame q) {
		List<Float> l = new ArrayList<Float>();
		Iterator<Challenge> it = q.getChallenges()
			.iterator();
		LatitudeLongitude f, s;
		Challenge itNext = it.hasNext() ? it.next() : null;

		while (true) {
			if (it.hasNext()) {
				f = itNext.getLocation();
			} else {
				break;
			}

			if (it.hasNext()) {
				itNext = it.next();
				s = itNext.getLocation();
			} else {
				break;
			}

			l.add(distance(f,
				s));
		}

		return l;
	}

	/**
	 * Populates a map with {@link Challenge} {@link ChallengeLocation} based on
	 * a {@link QuizWalkGame}w
	 * 
	 * @param m
	 *            Map to be populated
	 * @param q
	 *            QuizWalkGame of which ChallengeLocations will be presented
	 * @return boolean for debugg purposes
	 */
	public static boolean populateMap(GoogleMap m, QuizWalkGame q) {
		checkNotNull(q);
		checkNotNull(m);
	
		List<Challenge> challenges = q.getChallenges();
	
		Iterator<Challenge> itChallenge = challenges.iterator();
		Challenge currentIt;
		// extracts the locations of all the challenges
	
		while (itChallenge.hasNext()) {
			currentIt = itChallenge.next();
	
			// //TODO to make UC work, for now.
			m.addMarker(new MarkerOptions().position(Utilities.coordinatesToLatLng(currentIt.getLocation())))
				.setTitle(currentIt.getChallengeDescription());
	
		}
	
		// TODO: Add proper use of return value
		return true;
	}

}
