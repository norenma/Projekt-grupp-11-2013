package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.utils;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Media;

/**
 * Global identifiers here.
 */
public class Constants {

	// ORMLite Keys
	public final static String keyQuizWalkGametId = "QuizWalkGameId";

	/**
	 * The radius of which, if entered, the marker proximity alert will be fired
	 */
	public final static int MARKER_PROXIMITY_RADIUS = 10;

	/**
	 * Identifiers for different types of {@link Media}.
	 */
	public static enum MediaType {

		/**
		 * This media is of type <code>String</code>.
		 */
		STRING_MEDIA,

		/**
		 * This media is of type <code>Image</code>.
		 */
		IMAGE_MEDIA;
	}

	// TODO: Not yet relevant.
	// /**
	// * Some query to a server.
	// */
	//
	// public static class DatabaseQuery {
	// /**
	// * Retrieve a Set<QuizWalkGame>
	// */
	// public final static String GET_QUIZWALKS =
	// "quizwalk.server.get_quizwalks";
	//
	// }

}
