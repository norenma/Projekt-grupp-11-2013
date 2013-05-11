package se.chalmers.fonahano.quizwalk.model;

import static se.chalmers.fonahano.quizwalk.utils.Utilities.checkNotNullOrEmpty;

import java.io.Serializable;

import se.chalmers.fonahano.quizwalk.utils.Constants;
import se.chalmers.fonahano.quizwalk.utils.Constants.MediaType;

/**
 * Text question.
 */
public class StringQuestion extends Question implements Serializable {

	private final String question;
	private final Genre genre;

	/**
	 * Create a string question. Questions can be added to games.
	 * 
	 * @param question
	 */
	public StringQuestion(String question) {
		this.question = checkNotNullOrEmpty(question, "question can't be empty");
		//see the TODO in Question.java!
		genre = Genre.MISC;
	}

	@Override
	public MediaType getMediaType() {
		return Constants.MediaType.STRING_MEDIA;
	}

	/**
	 * @return the question string.
	 */
	@Override
	public String getMedia() {
		return question;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StringQuestion other = (StringQuestion) obj;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}

}
