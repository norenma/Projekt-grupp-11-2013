package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;


/**
 * An answer must be able to be compared to another answer.
 * Typical usage would be to see if one answer indeed corresponds to a correct answer. 
 */
public abstract class Answer {

	@Override
	abstract public boolean equals(Object o);
	
}
