package se.chalmers.fonahano.quizwalk.model;

import static se.chalmers.fonahano.quizwalk.model.Utilities.checkNotNullOrEmpty;
import se.chalmers.fonahano.quizwalk.interfaces.C;
import se.chalmers.fonahano.quizwalk.interfaces.C.Genre;
import se.chalmers.fonahano.quizwalk.interfaces.Question;

/**
 * Text question.
 */
public class StringQuestion implements Question<String> {
	private static final long serialVersionUID = 1L;

	private final String question;
	private final Genre genre;

	/**
	 * Create a string question. Questions can be added to games.
	 * 
	 * @param question
	 */
	public StringQuestion(String question) {
		this.question = checkNotNullOrEmpty(question,
			"question can't be empty");
		this.genre = C.Genre.MISC;
	}

	@Override
	public String get() {
		return question;
	}

	@Override
	public Genre getGenre() {
		return genre;
	}

	@Override
	public String toString() {
		return question;
	}

}
