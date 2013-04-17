package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.utils;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.List;

/**
 * Common static utility methods goes here.
 */
public class Utilities {

	/**
	 * Throws appropriate exception if the String is either null or empty.
	 * 
	 * @param stringToCheck
	 * @param errorMessage
	 * @return the String is no exceptions where thrown.
	 */
	public static String checkNotNullOrEmpty(String stringToCheck,
			Object errorMessage) {
		checkNotNull(stringToCheck);
		checkArgument(!stringToCheck.isEmpty(), errorMessage);
		return stringToCheck;
	}

	/**
	 * Throws appropriate exception if {@link java.lang.Map} object is either
	 * null, an element is null, or it's empty.
	 * 
	 * @param mapToCheck
	 * @param errorMessage
	 * @return the Map<K,V> if no exceptions where thrown.
	 */
	public static <K, V> java.util.Map<K, V> checkNotNullOrEmpty(
			java.util.Map<K, V> mapToCheck, Object errorMessage) {
		checkNotNull(mapToCheck);
		checkArgument(mapToCheck.isEmpty(), errorMessage);
		for (K e : mapToCheck.keySet()) {
			checkArgument(e != null, errorMessage + ":"
					+ "Some entry in map is null");
		}
		return mapToCheck;
	}

	/**
	 * 
	 * Throws appropriate exception if @link Collection} object is either null,
	 * empty, or contain null elements.
	 * 
	 * @param listToCheck
	 * @param errorMessage
	 * @return
	 */
	public static <T> Collection<T> checkNotNullOrEmpty(List<T> listToCheck,
			Object errorMessage) {
		checkNotNull(listToCheck);
		checkArgument(!listToCheck.isEmpty(), errorMessage);
		for (T t : listToCheck) {
			checkArgument(t != null, errorMessage + ":"
					+ "Some entry in Collection is null");
		}
		return listToCheck;
	}

}
