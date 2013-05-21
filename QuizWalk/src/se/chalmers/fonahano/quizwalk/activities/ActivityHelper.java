package se.chalmers.fonahano.quizwalk.activities;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import se.chalmers.fonahano.quizwalk.interfaces.LatitudeLongitude;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.ChallengeLocation;
import se.chalmers.fonahano.quizwalk.model.Coordinates;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import se.chalmers.fonahano.quizwalk.model.Utilities;
import android.location.Location;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Every GUI or activity related convenience methods should be here!
 * 
 */

//TODO: javaDoc till alla metoder
public abstract class ActivityHelper {


	private static void distance(LatitudeLongitude firstPos,
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
	 * 
	 * @param q
	 * @return
	 * 		The total distance between all questions in a quizwalk
	 */
	public static float totalQuizWalkDistance(QuizWalkGame q){
		Iterator<Float> dist = distance(q).iterator();
		float distance = 0;
		
		while (dist.hasNext()){
			distance += dist.next();
		}
		return distance;
	}
	
	private static Marker populateMap(GoogleMap map, Coordinates coordinates, String title){
		Marker marker = map.addMarker(new MarkerOptions().position(Utilities.coordinatesToLatLng(coordinates)));
		marker.setTitle(title);
		
		return marker;
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
	public static List<Marker> populateMap(GoogleMap map, QuizWalkGame q) {
		checkNotNull(q);
		checkNotNull(map);
	
		List<Challenge> challenges = q.getChallenges();
		List<Marker> markers = new ArrayList();
	
		Iterator<Challenge> itChallenge = challenges.iterator();
		Challenge currentIt;
		// extracts the locations of all the challenges
	
		while (itChallenge.hasNext()) {
			currentIt = itChallenge.next();
	
			Marker temp = populateMap(map, currentIt.getLocation(), currentIt.getChallengeDescription());
			markers.add(temp);
	
		}
		
		return markers;
	}
	/**
	 * Populates map with quizWalkLocations
	 * @param googleMap
	 * @param quizWalkGames
	 * @return
	 */
	public static List<Marker> populateMap(GoogleMap googleMap, List<QuizWalkGame> quizWalkGames){
		checkNotNull(googleMap);
		Utilities.checkNotNullOrEmpty(quizWalkGames, "the list of QuizWalkGames is empty");
		List<Marker> markers = new ArrayList();
		
		Iterator<QuizWalkGame> qIt = quizWalkGames.iterator();
		
		while(qIt.hasNext()){
			QuizWalkGame qItNext = qIt.next();
			Challenge c = checkNotNull(qItNext.getChallenges().get(0));
			markers.add(populateMap(googleMap, c.getLocation(), qItNext.getDescription()));
			
		}
		
		return markers;
		
	}

}
