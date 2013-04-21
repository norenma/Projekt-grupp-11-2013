package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.utils.Utilities.checkNotNullOrEmpty;

import java.util.HashSet;
import java.util.Set;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.map.Location;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

/**
 * A Challenge, in its most minimal form, contains a {@link Question} with its
 * corresponding and correct {@link Answer}. Challenges are integral parts of a
 * {@link QuizWalkGame} and should also involve geographical locations related
 * to the specific challenge. Populate your {@link QuizWalkGame} with
 * challenges.
 * 
 */
public class Challenge {

	/**
	 * A challenge can have different states in an active game of QuizWalk. For
	 * instance, if a player successfully completes a challenge - the state of
	 * that challenge will be <TT>COMPLETED</TT> for the particular player.
	 */
	public static enum ChallengeState {
		COMPLETED, IGNORED, FAILED, UNVISITED, DEFAULT;
	}

	/** The <code>Question</code> representing this Challenge */
	private final Question question;

	/**
	 * The correct answer to this challenge. Is always an entry in
	 * {@link #setOfAnswers}
	 */
	private final Answer correctAnswer;

	/** Description of this Challenge. Can be empty. */
	private final String challengeDescription;

	/** Set of available answers */
	private final Set<Answer> setOfAnswers;

	/** List of locations associated with this challenge, if any. */
	private final Set<Location> setOfLocations;

	/**
	 * Optionally, a <code>ChallengeReward</code> to be granted the
	 * <code>User</code> who completes this <code>Challenge</code>.
	 */
	private final Optional<ChallengeReward> challengeReward;

	/**
	 * Create a challenge.
	 * 
	 * @param challengeDescription
	 * @param question
	 * @param listOfAnswers
	 * @param correctAnswer
	 * @param listOfLocations
	 * @param challengeReward
	 */
	public Challenge(String challengeDescription, Question question,
			Set<Answer> listOfAnswers, Answer correctAnswer,
			Set<Location> listOfLocations,
			Optional<ChallengeReward> challengeReward) {

		this.question = checkNotNull(question);

		this.challengeDescription = checkNotNull(challengeDescription);

		this.setOfLocations = checkNotNull(listOfLocations);

		this.setOfAnswers = checkNotNullOrEmpty(listOfAnswers,
				"Set of answers must be present");

		// Set correctAnswer only if present in list.
		this.correctAnswer = checkNotNull(correctAnswer);
		checkArgument(listOfAnswers.contains(correctAnswer),
				"correctAnswer must be present in the set of answers");

		this.challengeReward = checkNotNull(challengeReward);

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
	 * @return the setOfLocations. Can be empty.
	 */
	public ImmutableSet<Location> getSetOfLocations() {
		return ImmutableSet.copyOf(setOfLocations);
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
		builder.append(", getSetOfLocations()=");
		builder.append(getSetOfLocations());
		builder.append(", getReward()=");
		builder.append(getReward());
		builder.append("]");
		return builder.toString();
	}

}