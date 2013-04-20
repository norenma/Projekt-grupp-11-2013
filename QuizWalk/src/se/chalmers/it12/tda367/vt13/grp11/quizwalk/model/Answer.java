package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

/**
 * An answer must be able to be compared to another answer. It also contain some
 * {@link Media} implementation. Typical usage in a {@link Challenge} for
 * <TT>Answer</TT> would be to see if an answer indeed corresponds to the to
 * correct answer in that same <tt>Challenge</tt>
 */
public abstract class Answer implements Media {

	/**
	 * Checks whether this answer is the same answer. Should be based on the
	 * {@link Media} that is implemented.
	 * 
	 * @param answer
	 *            should be the other {@link Answer} d
	 * @return <TT>true</TT> only if these two answers are same.
	 */
	public abstract boolean equals(Object answer);

	/**
	 * A hashCode based on the implemented properties of the {@link Media}
	 */
	public abstract int hashCode();
}
