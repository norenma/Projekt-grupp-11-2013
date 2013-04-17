package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.map.Location;

/**
 * A challenge is in its most minimal form contains a {@link Question} with its
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

	/** Description of this Challenge */
	private final String challengeDescription;

	/** The question representing this Challenge */
	private final Question question;

	/** Set of available answers */
	private final Set<Answer> setOfAnswers;

	/**
	 * The correct answer to this challenge. Is always an entry in
	 * {@link #setOfAnswers}
	 */
	private final Answer correctAnswer;

	/** List of locations associated with this challenge */
	private final Set<Location> setOfLocations;

	/** Reward for this challenge, can be null if no reward is given. */
	private final ChallengeReward challengeReward;

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
		checkArgument(listOfAnswers.contains(correctAnswer),
				"correctAnswer must be present in the set of answers");

		this.challengeReward = challengeReward;

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