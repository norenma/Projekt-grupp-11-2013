package se.chalmers.fonahano.quizwalk.model;

import java.io.Serializable;

/**
 * Some <code>Question</code> to be added into {@link Challenge}s you create.
 * 
 */
public abstract class Question implements Media, Serializable {
	private static final long serialVersionUID = 1L;

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
	
	/**
	 * Describes the genre of the question
	 * @author Hampus Forsvall, forzvall@gmail.com
	 *
	 */
	//TODO: Will need a more thorough implementation. Discussion will be held in a later iteration
	public enum Genre{
		POLITICS, SPORTS, MOVIES, MUSIC, HISTORY, GEOGRAPHY, SCIENCE, MISC
	}

}
