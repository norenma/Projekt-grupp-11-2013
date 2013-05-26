package se.chalmers.fonahano.quizwalk.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static se.chalmers.fonahano.quizwalk.model.Utilities.checkNotNullOrEmpty;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import se.chalmers.fonahano.quizwalk.interfaces.Answer;
import se.chalmers.fonahano.quizwalk.interfaces.Image;
import se.chalmers.fonahano.quizwalk.interfaces.Question;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * A Challenge, in its most minimal form, contains a {@link Question} with its
 * corresponding and correct {@link Answer}. Challenges are integral parts of a
 * {@link QuizWalkGame} and should also involve geographical locations related
 * to the specific challenge. Populate your {@link QuizWalkGame} with
 * challenges.
 * 
 */
@DatabaseTable
public class Challenge implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Builder pattern class for Challenge. "Builder"-class makes it easier to
	 * create a Challenge. Useful to be called from GUI that lets users to
	 * create Challenges. Every method returns the same Builder it was called
	 * upon, enabling "method chaining". Call build() to get the
	 * Challenge-object.
	 * 
	 * Be sure that you set <code>correctAnswer()</code> and
	 * <code>question()</code> before calling <code>build()</code>
	 */
	public static class Builder {
		private Question<String> question;
		private Answer<String> correctAnswer;
		private String description;
		private Set<Answer<String>> setOfAnswers;
		private ChallengeLocation location;
		private Optional<ChallengeReward> challengeReward;

		public Builder() {
			description = "";
			setOfAnswers = new HashSet<Answer<String>>();
			location = new ChallengeLocation(0d, 0d, "default location",
					Optional.<Image> absent());
			challengeReward = Optional.<ChallengeReward> absent();
		}

		/**
		 * Constructor to create a builder from already built challenge
		 * 
		 * @param c
		 *            the challenge to make a new builder from.
		 */
		public Builder(Challenge c) {
			question = c.question;
			correctAnswer = c.correctAnswer;
			description = c.challengeDescription;
			setOfAnswers = c.setOfAnswers;
			location = c.location;
			challengeReward = c.challengeReward;
		}

		/**
		 * @param d
		 *            the description.
		 */
		public Builder description(String d) {
			this.description = d;
			return this;
		}

		public Builder question(String q) {
			this.question = new StringQuestion(checkNotNullOrEmpty(q,
					"Question can't be empty."));
			return this;
		}

		public Builder question(Question<String> s) {
			this.question = s;
			return this;
		}

		/**
		 * @param a
		 *            the correct answer to this Challenge. The builder will
		 *            create a StringAnswer-object. (This will add the correct
		 *            answer to the setOfAnswers as well.)
		 */
		public Builder correctAnswer(String a) {
			this.correctAnswer = new StringAnswer(checkNotNullOrEmpty(a,
					"correctAnswer can't be empty"));
			setOfAnswers.add(correctAnswer);
			return this;

		}

		public Builder correctAnswer(Answer<String> a) {
			this.correctAnswer = a;
			return this;
		}

		public Builder addIncorrectAnswer(String a) {
			setOfAnswers.add(new StringAnswer(checkNotNullOrEmpty(a,
					"incorrect answer can't be empty")));
			return this;
		}

		public Builder addIncorrectAnswer(Answer<String> a) {
			setOfAnswers.add(a);
			return this;
		}

		public Builder addSetOfIncorrectAnswers(Set<Answer<String>> l) {
			setOfAnswers = l;
			return this;
		}

		/**
		 * @param l
		 *            location to add.
		 */
		public Builder location(ChallengeLocation l) {
			location = checkNotNull(l);
			return this;
		}

		public Builder location(double latitude, double longitude) {
			ChallengeLocation l = new ChallengeLocation(latitude, longitude,
					latitude + ":" + longitude, Optional.<Image> absent());
			return location(l);
		}

		/**
		 * @param r
		 *            reward to add.
		 */
		public Builder challengeReward(ChallengeReward r) {
			challengeReward = Optional.fromNullable(r);
			return this;
		}

		/**
		 * ONLY CALL THIS IF YOU ALREADY SET <code>question</code> and
		 * <code>correctAnswer</code>.
		 * 
		 * @return the Challenge defined in the builder.
		 */
		public Challenge build() {
			return new Challenge(description, question, setOfAnswers,
					correctAnswer, location, challengeReward);
		}

		/**
		 * @return the question
		 */
		public Question<String> getQuestion() {
			return question;
		}

		/**
		 * @return the correctAnswer
		 */
		public Answer<String> getCorrectAnswer() {
			return correctAnswer;
		}

		/**
		 * @return the challengeDescription
		 */
		public String getChallengeDescription() {
			return description;
		}

		/**
		 * @return the setOfAnswers, MUTABLE!
		 */
		public Set<Answer<String>> getSetOfAnswers() {
			return setOfAnswers;
		}

		/**
		 * @return the location.
		 */
		public ChallengeLocation getLocation() {
			return location;
		}

		/**
		 * @return the challengeReward
		 */
		public Optional<ChallengeReward> getChallengeReward() {
			return challengeReward;
		}

	}

	// Class vars
	@DatabaseField(generatedId = true)
	private int id;
	/** The <code>Question</code> representing this Challenge */
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private final Question<String> question;

	/**
	 * The correct answer to this challenge. Is always an entry in
	 * {@link #listOfAnswers}
	 */
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private final Answer<String> correctAnswer;

	/** Description of this Challenge. Can be empty. */

	@DatabaseField
	private final String challengeDescription;

	/** Set of available answers */
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private final HashSet<Answer<String>> setOfAnswers;

	/** List of locations associated with this challenge, if any. */
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private final ChallengeLocation location;

	/**
	 * Optionally, a <code>ChallengeReward</code> to be granted the
	 * <code>QuizWalkUser</code> who completes this <code>Challenge</code>.
	 */
	@DatabaseField
	private final Optional<ChallengeReward> challengeReward;

	// Can't do this.
	Challenge() {
		question = null;
		correctAnswer = null;
		challengeDescription = null;
		setOfAnswers = null;
		location = null;
		challengeReward = null;
	}

	/**
	 * Create a challenge.
	 */
	public Challenge(String challengeDescription, Question<String> question,
			Set<Answer<String>> setOfAnswers, Answer<String> correctAnswer,
			ChallengeLocation location,
			Optional<ChallengeReward> challengeReward) {

		this.question = checkNotNull(question);

		this.challengeDescription = checkNotNull(challengeDescription);

		this.location = checkNotNull(location);

		this.setOfAnswers = new HashSet<Answer<String>>(checkNotNullOrEmpty(
				setOfAnswers, "Set of answers must be present"));

		this.correctAnswer = checkNotNull(correctAnswer);
		// Set correctAnswer only if it's present in list.
		checkArgument(setOfAnswers.contains(correctAnswer),
				"correctAnswer must be present in the set of answers");

		this.challengeReward = checkNotNull(challengeReward);

	}

	/**
	 * Creates a copy of the specified Challenge.
	 * 
	 * @param c
	 *            to copy
	 */
	public Challenge(Challenge c) {
		this.question = c.getQuestion();
		this.correctAnswer = c.getCorrectAnswer();
		this.challengeDescription = c.getChallengeDescription();
		this.setOfAnswers = new HashSet<Answer<String>>(c.getSetOfAnswers());
		this.location = c.getLocation();
		this.challengeReward = c.getReward();

	}

	/**
	 * Checks if the argument is indeed the correct answer in this challenge.
	 * 
	 * @param answer
	 *            to be compared.
	 * @return <TT>TRUE</TT> only if the supplied argument equals
	 *         {@link #correctAnswer}
	 */
	public boolean isCorrectAnswer(Answer<String> answer) {
		return correctAnswer.equals(answer);
	}

	public boolean isCorrectAnswer(String answer) {
		return correctAnswer.equals(new StringAnswer(answer));
	}

	/**
	 * @return the question
	 */
	public Question<String> getQuestion() {
		return question;
	}

	/**
	 * @return the correctAnswer
	 */
	public Answer<String> getCorrectAnswer() {
		return correctAnswer;
	}

	/**
	 * @return the challengeDescription. Can be empty.
	 */
	public String getChallengeDescription() {
		return challengeDescription;
	}

	/**
	 * @return the setOfAnswers.
	 */
	public ImmutableSet<Answer<String>> getSetOfAnswers() {
		return ImmutableSet.copyOf(setOfAnswers);
	}

	/**
	 * @return the {@link #location}. Can be empty.
	 */
	public ChallengeLocation getLocation() {
		return location;
	}

	/**
	 * @return the reward of this Challenge, if available.
	 */
	public Optional<ChallengeReward> getReward() {
		return challengeReward;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((challengeDescription == null) ? 0 : challengeDescription
						.hashCode());
		result = prime * result
				+ ((challengeReward == null) ? 0 : challengeReward.hashCode());
		result = prime * result
				+ ((correctAnswer == null) ? 0 : correctAnswer.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		result = prime * result
				+ ((setOfAnswers == null) ? 0 : setOfAnswers.hashCode());
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
		Challenge other = (Challenge) obj;
		if (challengeDescription == null) {
			if (other.challengeDescription != null)
				return false;
		} else if (!challengeDescription.equals(other.challengeDescription))
			return false;
		if (challengeReward == null) {
			if (other.challengeReward != null)
				return false;
		} else if (!challengeReward.equals(other.challengeReward))
			return false; // challengeReward
		if (correctAnswer == null) {
			if (other.correctAnswer != null)
				return false;
		} else if (!correctAnswer.equals(other.correctAnswer)) // Answer
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location)) // Location
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question)) // Question
			return false;
		if (setOfAnswers == null) {
			if (other.setOfAnswers != null)
				return false;
		} else if (!setOfAnswers.equals(other.setOfAnswers))
			return false;
		return true;
	}

}