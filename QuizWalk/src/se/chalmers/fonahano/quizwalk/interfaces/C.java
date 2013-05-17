package se.chalmers.fonahano.quizwalk.interfaces;

/**
 * Global identifiers here. Constants, Keys, etc.
 */
public interface C {

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
	 * Genres for a {@link Question}
	 * 
	 */
	public static enum Genre {

		MISC, SPORT, SCIENCE, GEOGRAPHY;
	}
}