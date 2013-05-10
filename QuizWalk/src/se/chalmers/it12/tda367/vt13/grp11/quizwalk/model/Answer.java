package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

import java.io.Serializable;

import com.j256.ormlite.table.DatabaseTable;

/**
 * An answer must be able to be compared to another answer. It also contain some
 * {@link Media} implementation. Typical usage in a {@link Challenge} for
 * <TT>Answer</TT> would be to see if an answer indeed corresponds to the to
 * correct answer in that same <tt>Challenge</tt>.
 */
@DatabaseTable
public abstract class Answer implements Media, Serializable {

	/**
	 * An equals() based on the implemented {@link Media}
	 * 
	 * @return <code>true</code> if the implemented media has same properties.
	 *         <code>false</code> otherwise.
	 */
	public abstract boolean equals(Object answer);

	/**
	 * A <code>hashCode()</code> based on the implemented {@link Media}
	 */
	public abstract int hashCode();

	@Override
	public String toString() {
		return "Answer of " + getMediaType() + ":" + getMedia();
	}
}
