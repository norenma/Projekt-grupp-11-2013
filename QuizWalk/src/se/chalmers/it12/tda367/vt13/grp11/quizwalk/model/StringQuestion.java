package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

import static se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.utils.Utilities.checkNotNullOrEmpty;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.utils.Constants;

/**
 * Text questions.
 */
public class StringQuestion extends Question {

	private final String question;

	/**
	 * Create a string question. Questions can be added to games.
	 * 
	 * @param question
	 */
	public StringQuestion(String question) {
		this.question = checkNotNullOrEmpty(question, "question can't be empty");
	}

	@Override
	public String getMediaID() {
		return Constants.MediaID.STRING;
	}

	/**
	 * @return the question string.
	 */
	@Override
	public Object getMedia() {
		return question;
	}

}
