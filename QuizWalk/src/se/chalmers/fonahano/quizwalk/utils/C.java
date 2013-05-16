package se.chalmers.fonahano.quizwalk.utils;

import se.chalmers.fonahano.quizwalk.model.Media;

/**
 * Global identifiers here. Constants, Keys, etc.
 */
public class C {


    /**
     * Public message key for proximity alert
     */
    public final static String PROXIMITY_ALERT_MESSAGE = "se.chalmers.fonahano.quizwalk.activitiesMESSAGE";

    /**
	 * Database constants
	 *
	 */
	public static class Data {

		public final static String QUIZ_WALK_GAME_ID = "QuizWalkGameId";
		
		public final static String JSON_DATA = "se.chalmers.fonahano.quizwalk.json_data";

		// name of the database file for your application
		public static final String DATABASE_NAME = "QuizWalkDB.sqlite";
	}

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
