package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.connections;

/**
 * A server Query. The query should contain a command, and an attached Object.
 * Create a query and use it to pass it to some Class implementing
 * {@link IServerDatabase}
 * 
 */
public interface Query {

	/**
	 * @return A string query. Will usually be some command that a Server can
	 *         parse.
	 */
	public String getQuery();

	/**
	 * @return the requested object
	 */
	public Object getObject();
}
