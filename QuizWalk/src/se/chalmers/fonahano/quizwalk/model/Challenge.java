package se.chalmers.fonahano.quizwalk.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.utils.Utilities.checkNotNullOrEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import se.chalmers.fonahano.quizwalk.map.Location;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
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
		private Question question;
		private Answer correctAnswer;
		private String challengeDescription;
		private Set<Answer> setOfAnswers;
		private List<Location> listOfLocations;
		private Optional<ChallengeReward> challengeReward;

		public Builder() {
			challengeDescription = "";
			setOfAnswers = new HashSet<Answer>();
			listOfLocations = new ArrayList<Location>();
			challengeReward = Optional.<ChallengeReward> absent();
		}

		public Builder question(Question q) {
			this.question = checkNotNull(q);
			return this;
		}

		public Builder question(String q) {
			this.question = new StringQuestion(checkNotNullOrEmpty(q,
					"Question can't be empty."));
			return this;
		}

		/**
		 * @param a
		 *            the correct answer to this Challenge. (This will add the
		 *            correct answer to the setOfAnswers as well.)
		 */
		public Builder correctAnswer(Answer a) {
			this.correctAnswer = checkNotNull(a);
			setOfAnswers.add(a);
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

		/**
		 * @param d
		 *            the description.
		 */
		public Builder challengeDescription(String d) {
			this.challengeDescription = d;
			return this;
		}

		/**
		 * @param a
		 *            an incorrect answer.
		 */
		public Builder addIncorrectAnswer(Answer a) {
			setOfAnswers.add(a);
			return this;
		}

		/**
		 * @param l
		 *            location to add.
		 */
		public Builder addLocation(Location l) {
			listOfLocations.add(l);
			return this;
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
			return new Challenge(challengeDescription, question, setOfAnswers,
					correctAnswer, listOfLocations, challengeReward);
		}

		/**
		 * @return the question
		 */
		public Question getQuestion() {
			return question;
		}

		/**
		 * @return the correctAnswer
		 */
		public Answer getCorrectAnswer() {
			return correctAnswer;
		}

		/**
		 * @return the challengeDescription
		 */
		public String getChallengeDescription() {
			return challengeDescription;
		}

		/**
		 * @return the setOfAnswers, MUTABLE!
		 */
		public Set<Answer> getSetOfAnswers() {
			return setOfAnswers;
		}

		/**
		 * @return the listOfLocations, MUTABLE!
		 */
		public List<Location> getListOfLocations() {
			return listOfLocations;
		}

		/**
		 * @return the challengeReward
		 */
		public Optional<ChallengeReward> getChallengeReward() {
			return challengeReward;
		}

	}

	/** The <code>Question</code> representing this Challenge */
	@DatabaseField
	private final Question question;

	/**
	 * The correct answer to this challenge. Is always an entry in
	 * {@link #listOfAnswers}
	 */
	@DatabaseField
	private final Answer correctAnswer;

	/** Description of this Challenge. Can be empty. */

	@DatabaseField
	private final String challengeDescription;

	/** Set of available answers */
	@DatabaseField
	private final Set<Answer> setOfAnswers;

	/** List of locations associated with this challenge, if any. */
	@DatabaseField
	private final List<Location> listOfLocations;

	/**
	 * Optionally, a <code>ChallengeReward</code> to be granted the
	 * <code>User</code> who completes this <code>Challenge</code>.
	 */
	@DatabaseField
	private final Optional<ChallengeReward> challengeReward;

	// Can't do this.
	@SuppressWarnings("unused")
	private Challenge() {
		question = null;
		correctAnswer = null;
		challengeDescription = null;
		setOfAnswers = null;
		listOfLocations = null;
		challengeReward = null;
	}

	/**
	 * Create a challenge.
	 */
	public Challenge(String challengeDescription, Question question,
			Set<Answer> setOfAnswers, Answer correctAnswer,
			List<Location> listOfLocations,
			Optional<ChallengeReward> challengeReward) {

		this.question = checkNotNull(question);

		this.challengeDescription = checkNotNull(challengeDescription);

		this.listOfLocations = checkNotNull(listOfLocations);

		this.setOfAnswers = checkNotNullOrEmpty(setOfAnswers,
				"Set of answers must be present");

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
		this.setOfAnswers = new HashSet<Answer>(c.getSetOfAnswers());
		this.listOfLocations = new ArrayList<Location>(c.getListOfLocations());
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
	public final boolean isCorrectAnswer(Answer answer) {
		return correctAnswer.equals(answer);
	}

	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @return the correctAnswer
	 */
	public Answer getCorrectAnswer() {
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
	public ImmutableSet<Answer> getSetOfAnswers() {
		return ImmutableSet.copyOf(setOfAnswers);
	}

	/**
	 * @return the {@link #listOfLocations}. Can be empty.
	 */
	public ImmutableList<Location> getListOfLocations() {
		return ImmutableList.copyOf(listOfLocations);
	}

	/**
	 * @return the reward of this Challenge, if available.
	 */
	public Optional<ChallengeReward> getReward() {
		return challengeReward;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Challenge [getQuestion()=");
		builder.append(getQuestion());
		builder.append(", getCorrectAnswer()=");
		builder.append(getCorrectAnswer());
		builder.append(", getChallengeDescription()=");
		builder.append(getChallengeDescription());
		builder.append(", getSetOfAnswers()=");
		builder.append(getSetOfAnswers());
		builder.append(", getListOfLocations()=");
		builder.append(getListOfLocations());
		builder.append(", getReward()=");
		builder.append(getReward());
		builder.append("]");
		return builder.toString();
	}

}