package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.utils.Utilities.checkNotNullOrEmpty;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Challenge.ChallengeState;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Game.State;

/**
 * A game of QuizWalk! This class will retain all information needed to describe
 * a whole set of {@link Challenge}s, including their current
 * {@link Challenge.ChallengeState}.
 * 
 */
public class QuizWalkGame extends Game {

	/**
	 * Description of this QuizWalkGame. Describing the set of challenges
	 * included.
	 */
	private final String description;

	/**
	 * Image representing this Game.
	 */
	private final Image image;

	/**
	 * The challenges that constitute this QuizWalk. Associated with every
	 * challenge is its current state defined in enum
	 * {@link Challenge.ChallengeState}.
	 */
	private final java.util.Map<Challenge, ChallengeState> challenges;

	/**
	 * Create a new QuizWalkGame.
	 * 
	 * @param description
	 * @param image
	 * @param challenges
	 */
	public QuizWalkGame(String description, Image image,
			java.util.Map<Challenge, ChallengeState> challenges) {

		this.description = checkNotNull(description);
		checkArgument(!description.isEmpty(), "description can't be empty");

		this.image = checkNotNull(image);
		this.challenges = checkNotNullOrEmpty(challenges,
				"challanges must contain at least one entry and a non-null value.");
	}

	/**
	 * Returns {@link State.RUNNING} if not all {@link #challenges}. More
	 * formally, if at least one challenge in this game is
	 * <TT>Challenge.State.UNVISITED</TT> then the game is still running.
	 */
	@Override
	public State getState() {

		State s = State.GAME_OVER;

		for (Challenge c : challenges.keySet()) {
			if (challenges.get(c).equals(ChallengeState.UNVISITED))
				s = State.RUNNING;
		}
		return s;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @return the challenges
	 */
	public java.util.Map<Challenge, ChallengeState> getChallenges() {
		return challenges;
	}
}
