package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.connections;

/**
 * Represents the clients communication with the QuizWalk Server.
 * 
 */
public interface IServerDatabase {

	/**
	 * @param query
	 *            to be sent to the server.
	 * @return a {@link Response}, that may contain a requested Object.
	 */
	public <T> Response<T> query(Query query);

}
