package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.utils.Constants;

public class StringQuestion extends Question {

	private final String question;

	/**
	 * Create a string question. Questions can be added to games.
	 * 
	 * @param question
	 */
	public StringQuestion(String question) {
		this.question = checkNotNull(question);
		checkArgument(!question.isEmpty(), "question can't be empty");
	}

	@Override
	public String getMediaID() {
		return Constants.MediaID.STRING;
	}

	@Override
	public Object getMedia() {
		return getQuestion();
	}

	@Override
	public String getQuestion() {
		return question;
	}

}
