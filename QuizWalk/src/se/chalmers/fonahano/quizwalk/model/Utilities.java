package se.chalmers.fonahano.quizwalk.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Map;

import se.chalmers.fonahano.quizwalk.interfaces.LatitudeLongitude;

import com.google.android.gms.maps.model.LatLng;

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
		checkArgument(!stringToCheck.isEmpty(), errorMessage);
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
		checkArgument(!mapToCheck.isEmpty(), errorMessage);
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
		checkArgument(!collectionToCheck.isEmpty(), errorMessage);
		for (E t : collectionToCheck) {
			checkArgument(t != null, errorMessage + ":"
					+ "Some entry in Collection is null");
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
	public static LatLng coordinatesToLatLng(LatitudeLongitude c) {
		return new LatLng(c.getLatitude(), c.getLongitude());
	}

	public static LatitudeLongitude latLngToCoordinates(LatLng latLong) {
		return new Coordinates(latLong.latitude, latLong.longitude);
	}

}