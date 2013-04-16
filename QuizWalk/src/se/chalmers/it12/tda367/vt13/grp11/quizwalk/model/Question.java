package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

public abstract class Question {

	// TODO: Abstract Question should NOT return by default a String. Since
	// there will probably only be two media types (Strings and Images) for
	// Question/Answers we should soon hard code this feature.
	public abstract String getQuestion();

}
