package se.chalmers.fonahano.quizwalk.presentation;

/**
 * Global identifiers here. Constants, Keys, etc.
 */
public interface C {

	/**
	 * Activity intents etc should be in this class atm.
	 * 
	 */
	public static class Intent {

		public static class Action {

			public static final String STATE_CHANGED_ACTIVE_QUIZWALK = "se.chalmers.fonahano.quizwalk.STATE_CHANGED_QUIZWALK";
			public static final String STATE_CHANGED_QUIZWALK_BUILDER = "se.chalmers.fonahano.quizwalk.STATE_CHANGED_QUIZWALK_BUILDER";
			public static final String STATE_CHANGED_CHALLENGE_BUILDER = "se.chalmers.fonahano.quizwalk.STATE_CHANGED_CHALLENGE_BUILDER";
			public static final String STATE_CHANGED_CURRENT_LOCATION = "se.chalmers.fonahano.quizwalk.STATE_CHANGED_CURRENT_LOCATION";
			public static final String STATE_CHANGED_COMPLETED_QUIZWALK = "se.chalmers.fonahano.quizwalk.STATE_CHANGED_COMPLETED_QUIZWALK";
			public static final String EDIT_NEW_QUIZ_WALK = "se.chalmers.fonahano.quizwalk.EDIT_NEW_QUIZ_WALK";
		}

		public static class Extra {

			public static class GameMap {
				public final static String MAP_STATE = "MAP_STATE";
			}

			/**
			 * Public message key for proximity alert
			 */
			public final static String PROXIMITY_ALERT_MESSAGE = "se.chalmers.fonahano.quizwalk.activitiesMESSAGE";
			/**
			 * The radius of which, if entered, the marker proximity alert will
			 * be fired
			 */
			public final static int MARKER_PROXIMITY_RADIUS = 50;

		}

	}

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
}