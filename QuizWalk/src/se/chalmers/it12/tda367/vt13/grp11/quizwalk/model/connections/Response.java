package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.connections;

/**
 * A response for bundling data. A Response could be a list of
 */
public interface Response<T> {

	/**
	 * @return Message related to this Response.
	 */
	public String getResponseMessage();

	/**
	 * @return <TT>1</TT> if success. <TT>-1<TT> if failed.
	 */
	public int getResponseStatus();

	/**
	 * @return the requested object
	 */
	public T getObject();

}
