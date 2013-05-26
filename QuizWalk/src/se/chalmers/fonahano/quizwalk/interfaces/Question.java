package se.chalmers.fonahano.quizwalk.interfaces;

import java.io.Serializable;

import se.chalmers.fonahano.quizwalk.model.Challenge;

/**
 * Some <code>Question</code> to be added into {@link Challenge}s you create.
 * 
 */
public interface Question<T> extends Serializable {

	/**
	 * Genres for a {@link Question}
	 * 
	 */
	public static enum Genre {

		MISC, SPORT, SCIENCE, GEOGRAPHY;
	}

	/**
	 * @return the question.
	 */
	public abstract T get();

	/**
	 * @return the genre of this Question.
	 */
	public abstract Question.Genre getGenre();

	/**
	 * Convenience method.
	 * 
	 * @return The Question in string-form. If the question is a Question of
	 *         strings this should return the same as <code>get()</code>.
	 */
	public abstract String toString();

	public abstract boolean equals(Object answer);

}
