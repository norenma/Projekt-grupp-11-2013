package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

public class StringQuestion extends Question {

	private final String question;
	
	public StringQuestion(String question) {
		this.question = question;
	}

	@Override
	public String getQuestion() {
		return question;
	}
	
}
