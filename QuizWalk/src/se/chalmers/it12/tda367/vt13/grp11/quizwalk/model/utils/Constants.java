package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.utils;

/**
 * Global identifiers here.
 */
public class Constants {

	/**
	 * Identifiers for different types of
	 * {@link se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Media}.
	 */
	public static class MediaID {

		/**
		 * A String media.
		 */
		public final static String STRING = "quizwalk.media.string";

		/**
		 * An Image media.
		 */
		public final static String IMAGE = "quizwalk.media.image";
	}

	/**
	 * Some query to a server.
	 */
	public static class DatabaseQuery {
		/**
		 * Retrieve a Set<QuizWalkGame>
		 */
		public final static String GET_QUIZWALKS = "quizwalk.server.get_quizwalks";

	}

}
