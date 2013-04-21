package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

import static com.google.common.base.Preconditions.checkNotNull;
import static se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.utils.Utilities.checkNotNullOrEmpty;

import java.util.Map;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Challenge.ChallengeState;

import com.google.common.base.Optional;

/**
 * A game of QuizWalk! This class will contain all information needed to
 * describe a whole set of {@link Challenge}s, including their current
 * {@link Challenge.ChallengeState}.
 * 
 */
public class QuizWalkGame extends Game {

	/**
	 * Name of this game of QuizWalk.
	 */
	private final String name;

	/**
	 * Description of this QuizWalkGame. Describing the set of challenges
	 * included. Can be empty.
	 */
	private final String description;

	/**
	 * Image representing this Game.
	 */
	private final Optional<Image> image;

	/**
	 * The challenges that constitute this QuizWalk. Associated with every
	 * challenge in an active game is its current state defined in Enum
	 * {@link Challenge.ChallengeState}.
	 */
	private final Map<Challenge, ChallengeState> challenges;

	/**
	 * The reward, if any, granted to user who completes this QuizWalk.
	 */
	private final Optional<QuizWalkGameReward> reward;

	/**
	 * Create a new QuizWalkGame.
	 * 
	 * @param name
	 *            of this game. Can't be empty.
	 * @param description
	 *            of this game, if any.
	 * @param image
	 *            of this game, if any.
	 * @param challenges
	 *            of this game. Can't be empty.
	 * @param reward
	 *            of this game, if any.
	 */
	public QuizWalkGame(String name, String description, Optional<Image> image,
			Map<Challenge, ChallengeState> challenges,
			Optional<QuizWalkGameReward> reward) {

		this.name = checkNotNullOrEmpty(name, "name can't be non-empty.");

		this.description = checkNotNull(description);

		this.image = checkNotNull(image);

		this.challenges = checkNotNullOrEmpty(challenges,
				"challenges can't be empty");

		this.reward = checkNotNull(reward);
	}

	/**
	 * Returns if the game is still running. More formally, if at least one
	 * challenge in this game is <TT>ChallengeState.UNIVISTED</TT> is is
	 * considered to be RUNNING.
	 * 
	 * @return {@link Game.State#RUNNING} if not all challenges have been
	 *         interacted with. {@link Game.State#GAME_OVER} otherwise.
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
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the description. Can be empty.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the image if available.
	 */
	public Optional<Image> getImage() {
		return image;
	}

	//TODO: return immutable list instead. Not done for debugging purposes.
	/**
	 * @return the challenges
	 */
	public Map<Challenge, ChallengeState> getChallenges() {
		return challenges;
	}

	/**
	 * @return the reward
	 */
	public Optional<QuizWalkGameReward> getReward() {
		return reward;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuizWalkGame [getState()=");
		builder.append(getState());
		builder.append(", getName()=");
		builder.append(getName());
		builder.append(", getDescription()=");
		builder.append(getDescription());
		builder.append(", getImage()=");
		builder.append(getImage());
		builder.append(", getChallenges()=");
		builder.append(getChallenges());
		builder.append(", getReward()=");
		builder.append(getReward());
		builder.append("]");
		return builder.toString();
	}
}
