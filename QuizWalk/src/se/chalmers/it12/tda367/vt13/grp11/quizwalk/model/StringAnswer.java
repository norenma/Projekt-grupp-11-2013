package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.utils.Constants;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.utils.Constants.MediaType;

/**
 * An Answer in form of a text String. An answer can be either correct or
 * incorrect when they populate {@link Challenge}s.
 * 
 */
public class StringAnswer extends Answer {

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
	public MediaType getMediaType() {
		return Constants.MediaType.STRING_MEDIA;
	}

	@Override
	public String getMedia() {
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
		return answer.equals(other);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		return result;
	}


}
