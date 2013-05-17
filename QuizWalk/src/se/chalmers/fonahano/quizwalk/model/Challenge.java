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
	 * A challenge can have different states in an active game of QuizWalk. For
	 * instance, if a player successfully completes a challenge - the state of
	 * that challenge will be <TT>COMPLETED</TT> for the particular player. This
	 * class is used by {@link QuizWalkGame}.
	 */
	public static enum ChallengeState {
		/**
		 * Completed challenge.
		 */
		COMPLETED,

		FAILED,

		/**
		 * The challenge has been ignored.
		 */
		IGNORED,

		/**
		 * Not yet visited challenge. This is what all the active
		 * ChallengeStates should be in a newly initialized running
		 * QuizWalkGame.
		 */
		UNVISITED,

		/**
		 * The challenge is inactive or dormant. In a running game all
		 * challenges should be set to something else than <code>DEFAULT</code>
		 */
		DEFAULT;

	}

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
			location = new ChallengeLocation(0d,
				0d,
				"default location",
				Optional.<Image> absent());
			challengeReward = Optional.<ChallengeReward> absent();
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
			ChallengeLocation l = new ChallengeLocation(latitude,
				longitude,
				latitude + ":" + longitude,
				Optional.<Image> absent());
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
			return new Challenge(description,
				question,
				setOfAnswers,
				correctAnswer,
				location,
				challengeReward);
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
	 * <code>AndroidUser</code> who completes this <code>Challenge</code>.
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

		this.setOfAnswers = new HashSet<Answer<String>>(checkNotNullOrEmpty(setOfAnswers,
			"Set of answers must be present"));

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
	public final boolean isCorrectAnswer(Answer<String> answer) {
		return correctAnswer.equals(answer);
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

}