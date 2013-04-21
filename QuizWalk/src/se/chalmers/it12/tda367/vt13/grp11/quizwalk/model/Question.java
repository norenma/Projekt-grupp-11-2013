package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

/**
 * Some <code>Question</code> to be added into {@link Challenge}s you create.
 * 
 */
public abstract class Question implements Media {

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
		return getMedia().toString();
	}

}
