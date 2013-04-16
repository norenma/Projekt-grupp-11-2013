package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

/**
 * A challenge is a question with a correct answer. Challenges are integral
 * parts of a QuizWalkGame and often involve geographical locations related to
 * the specific challenge.
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

	/** Description of this challenge */
	private final String challengeDescription;

	/** The question representing this Challenge */
	private final Question question;

	/** List of available answers */
	private final Set<Answer> setOfAnswers;

	/** The correct answer to this challenge. {@link #setOfAnswers} */
	private final Answer correctAnswer;

	/** List of locations associated with this challenge */
	private final Set<Location> setOfLocations;

	/** Reward for this challenge, can be null if no reward is given. */
	private final ChallengeReward challengeReward;

	/**
	 * Create a new challenge.
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
			Set<Location> listOfLocations, ChallengeReward challengeReward) {

		this.challengeDescription = checkNotNull(challengeDescription);
		checkArgument(!challengeDescription.isEmpty(),
				"Description can't be empty");

		this.question = checkNotNull(question);

		this.setOfAnswers = checkNotNull(listOfAnswers);
		checkArgument(!listOfAnswers.isEmpty(),
				"Set of answers must be present");

		this.setOfLocations = checkNotNull(listOfLocations);

		// Set correctAnswer only if present in list.
		this.correctAnswer = checkNotNull(correctAnswer);
		checkArgument(!listOfAnswers.contains(correctAnswer),
				"correctAnswer must be present in the set of answers");

		this.challengeReward = challengeReward;

	}

	/**
	 * Checks if the parameter object is indeed the correct answer in this
	 * challenge.
	 * 
	 * @param answer
	 *            to be controlled
	 * @return true only if the supplied argument equals {@link #correctAnswer}
	 */
	public final boolean isCorrectAnswer(Answer answer) {
		return correctAnswer.equals(answer);
	}

	/**
	 * @return the challengeDescription
	 */
	public String getChallengeDescription() {
		return challengeDescription;
	}

	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @return the listOfAnswers
	 */
	public Set<Answer> getListOfAnswers() {
		return setOfAnswers;
	}

	/**
	 * @return the correctAnswer
	 */
	public Answer getCorrectAnswer() {
		return correctAnswer;
	}

	/**
	 * @return the listOfLocations
	 */
	public Set<Location> getListOfLocations() {
		return setOfLocations;
	}

	/**
	 * @return the reward. null if no reward is present.
	 */
	public ChallengeReward getReward() {
		return challengeReward;
	}

}