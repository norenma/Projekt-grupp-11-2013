package se.chalmers.fonahano.quizwalk.interfaces;

import java.io.Serializable;

import se.chalmers.fonahano.quizwalk.interfaces.C.Genre;
import se.chalmers.fonahano.quizwalk.model.Challenge;

/**
 * Some <code>Question</code> to be added into {@link Challenge}s you create.
 * 
 */
public interface Question<T> extends Serializable {

	
	public abstract boolean equals(Object answer);

	/**
	 * @return the question.
	 */
	public abstract T get();
	
	public abstract Genre getGenre();

}
