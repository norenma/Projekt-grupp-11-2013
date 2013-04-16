package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;


/**
 * A receipt that can for instance be returned on a database query.
 * The receipt will contain a message, and state (e.g. success/fail)
 */
public interface Receipt {

	public String getReceiptMessage();
	/**
	 * @return <TT>1</TT> if success. <TT>-1<TT> if failed.
	 */
	public int getReceiptStatus();
	
}
