package se.chalmers.fonahano.quizwalk.interfaces;

import java.io.Serializable;

import se.chalmers.fonahano.quizwalk.model.Challenge;

/**
 * An answer must be able to be compared to another answer. Typical usage in a
 * {@link Challenge} for <TT>Answer</TT> would be to see if an answer indeed
 * corresponds to the to correct answer in that same <tt>Challenge</tt>.
 * 
 * The most typical implementation of this is to have an Answer of Strings.
 */
public interface Answer<T> extends Serializable {

	/**
	 * An equals() based on implemented generic type
	 * 
	 * @return <code>true</code> if the implemented media has same properties.
	 *         <code>false</code> otherwise.
	 */
	public abstract boolean equals(Object answer);

	/**
	 * @return and answer.
	 */
	public abstract T getAnswer();

	/**
	 * Convenience method.
	 * 
	 * @return The Answer in string-form. If the question is a Answer of strings
	 *         this should return the same as <code>get()</code>.
	 */
	public abstract String toString();

}
