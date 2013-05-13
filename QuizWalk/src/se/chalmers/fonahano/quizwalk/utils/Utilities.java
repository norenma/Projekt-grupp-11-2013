package se.chalmers.fonahano.quizwalk.utils;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import se.chalmers.fonahano.quizwalk.map.Coordinates;
import se.chalmers.fonahano.quizwalk.map.ChallengeLocation;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Common static utility methods goes here.
 */
public class Utilities {

	/**
	 * Throws appropriate exception if the String is either null or empty.
	 * 
	 * @param stringToCheck
	 * @param errorMessage
	 *            to be shown if the string is empty.
	 * @return the String if no exceptions where thrown.
	 */
	public static String checkNotNullOrEmpty(String stringToCheck,
			Object errorMessage) {
		checkNotNull(stringToCheck);
		checkArgument(!stringToCheck.isEmpty(),
			errorMessage);
		return stringToCheck;
	}

	/**
	 * Throws appropriate exception if the {@link java.util.Map} object is
	 * either null, an element is null, or is empty.
	 * 
	 * @param mapToCheck
	 * @param errorMessage
	 *            to be shown if Map is empty.
	 * @return the Map object if no exceptions where thrown.
	 */
	public static <K, V> Map<K, V> checkNotNullOrEmpty(Map<K, V> mapToCheck,
			Object errorMessage) {
		checkNotNull(mapToCheck);
		checkArgument(!mapToCheck.isEmpty(),
			errorMessage);
		for (K e : mapToCheck.keySet()) {
			checkNotNull(e);
		}
		return mapToCheck;
	}

	/**
	 * 
	 * Throws appropriate exception if {@link Collection} object is either null,
	 * empty, or contain null elements.
	 * 
	 * @param <E>
	 * 
	 * @param collectionToCheck
	 * @param errorMessage
	 * @return the listToCheck if no exceptions are thrown.
	 */
	public static <T extends Collection<E>, E> T checkNotNullOrEmpty(
			T collectionToCheck, Object errorMessage) {
		checkNotNull(collectionToCheck);
		checkArgument(!collectionToCheck.isEmpty(),
			errorMessage);
		for (E t : collectionToCheck) {
			checkArgument(t != null,
				errorMessage + ":" + "Some entry in Collection is null");
		}
		return collectionToCheck;
	}

	/**
	 * Converts {@link Coordinates} to {@link LatLng}
	 * 
	 * @param c
	 *            Coordinates
	 * @return LatLng
	 */
	public static LatLng coordinatesToLatLng(Coordinates c) {
		return new LatLng(c.getLatitude(),
			c.getLongitude());
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
			m.addMarker(new MarkerOptions().position(coordinatesToLatLng(currentIt.getLocation())))
				.setTitle(currentIt.getChallengeDescription());

		}

		// TODO: Add proper use of return value
		return true;
	}

}