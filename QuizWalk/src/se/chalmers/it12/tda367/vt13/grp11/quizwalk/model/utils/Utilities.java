package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.utils;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.List;

/**
 * Common static utility methods goes here.
 */
public class Utilities {

	public static String checkNotNullOrEmpty(String stringToCheck,
			Object errorMessage) {
		checkNotNull(stringToCheck);
		checkArgument(!stringToCheck.isEmpty(), errorMessage);
		return stringToCheck;
	}

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

	public static <T> Collection<T> checkNotNullOrEmpty(List<T> listToCheck,
			Object errorMessage) {
		checkNotNull(listToCheck);
		checkArgument(!listToCheck.isEmpty(), errorMessage);
		return listToCheck;
	}

}
