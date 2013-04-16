package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

public class StringAnswer extends Answer {

	private final String answer;
	
	public StringAnswer(String answer) {
		this.answer = answer;
	}
	
	@Override
	public final boolean equals(Object o) {
		if (o == null)
			return false;
		if (o.getClass() != this.getClass())
			return false;
		else {
			StringAnswer rhs = (StringAnswer) o;
			return answer.equals(rhs);
		}
	}

}
