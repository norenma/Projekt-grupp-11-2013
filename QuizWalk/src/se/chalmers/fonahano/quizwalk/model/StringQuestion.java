package se.chalmers.fonahano.quizwalk.model;

import static se.chalmers.fonahano.quizwalk.model.Utilities.checkNotNullOrEmpty;
import se.chalmers.fonahano.quizwalk.interfaces.Question;

/**
 * Text question.
 */
public class StringQuestion implements Question<String> {
	private static final long serialVersionUID = 1L;

	private final String question;
	private final Question.Genre genre;

	/**
	 * Create a string question. Questions can be added to games.
	 * 
	 * @param question
	 */
	public StringQuestion(String question) {
		this.question = checkNotNullOrEmpty(question, "question can't be empty");
		this.genre = Question.Genre.MISC;
	}

	@Override
	public String get() {
		return question;
	}

	@Override
	public Question.Genre getGenre() {
		return genre;
	}

	@Override
	public String toString() {
		return question;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
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
		if (genre != other.genre)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}

}
