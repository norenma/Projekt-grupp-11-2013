package se.chalmers.fonahano.quizwalk.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import se.chalmers.fonahano.quizwalk.interfaces.Answer;

/**
 * An Answer in form of a text String. An answer can be either correct or
 * incorrect when they populate {@link Challenge}s.
 * 
 */
public class StringAnswer implements Answer<String> {
	private static final long serialVersionUID = 1L;
	/**
	 * An answer.
	 */
	private final String answer;

	/**
	 * Create a String answer. Include this in your {@link Challenge}
	 * 
	 * @param answer
	 */
	public StringAnswer(String answer) {
		this.answer = checkNotNull(answer);
		checkArgument(!answer.isEmpty(), "answer can't be empty");
	}

	@Override
	public String getAnswer() {
		return answer;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StringAnswer other = (StringAnswer) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return answer;
	}

}
