package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

import static com.google.common.base.Preconditions.checkNotNull;
import static se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.utils.Utilities.checkNotNullOrEmpty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Challenge.ChallengeState;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

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
	 * The challenges that constitute this QuizWalk in the order they are to be
	 * presented. More formally, <code>challenges.get(0)</code> represents the
	 * first Challenge - while higher index number indicate later access.
	 * Associated with every challenge in an active game is its current state
	 * defined in {@link #challengeStates}.
	 * 
	 */
	private final List<Challenge> challenges;

	/**
	 * Keeps track of the {@link ChallengeState}s of this game's
	 * <code>Challenge</code>s. This is done by means of key-value pairs where
	 * keys reflects (points to same object) as the elements of
	 * {@link #challenges}.
	 */
	private final Map<Challenge, ChallengeState> challengeStates;

	/**
	 * The reward, if any, granted to user who completes this QuizWalk.
	 */
	private final Optional<QuizWalkGameReward> reward;

	/**
	 * Create a new QuizWalkGame. This is a whole description of a game of
	 * QuizWalk - either running or dormant. This constructor sets all
	 * {@link ChallengeState}s to <code>ChallengeState.DEFAULT</code>.
	 * 
	 * @param name
	 *            of this game. Can't be empty.
	 * @param description
	 *            of this game, if any.
	 * @param image
	 *            of this game, if any.
	 * @param challenges
	 *            of this game and their order. A QuizWalkGame must contain at
	 *            least one challenge by definition.
	 * @param reward
	 *            of this game, if any.
	 */
	public QuizWalkGame(String name, String description, Optional<Image> image,
			List<Challenge> challenges, Optional<QuizWalkGameReward> reward) {

		this.name = checkNotNullOrEmpty(name, "name can't be non-empty.");

		this.description = checkNotNull(description);

		this.image = checkNotNull(image);

		this.challenges = checkNotNullOrEmpty(challenges,
				"list of challenges can't be empty");

		this.reward = checkNotNull(reward);

		this.challengeStates = new HashMap<Challenge, Challenge.ChallengeState>();

		// Add challenges from list and set them to default.
		for (Challenge c : challenges) {
			challengeStates.put(c, ChallengeState.DEFAULT);
		}
	}

	/**
	 * @return the challenges of this QuizWalk
	 */
	public ImmutableList<Challenge> getChallenges() {
		return ImmutableList.copyOf(challenges);
	}

	/**
	 * Returns the {@link GameState} of the game.
	 * 
	 * @return {@link Game.GameState#RUNNING} if not all challenges have been
	 *         interacted with. {@link GameState#GAME_OVER} otherwise.
	 */
	@Override
	public GameState getGameState() {

		GameState s = GameState.GAME_OVER;

		for (Challenge c : challengeStates.keySet()) {
			if (challengeStates.get(c).equals(ChallengeState.UNVISITED))
				s = GameState.RUNNING;
		}
		return s;
	}

	/**
	 * Sets a Challenge in this game to a particular {@link GameState}. (Don't
	 * set to DEFAULT - as its implementation during a RUNNING GAME is not
	 * defined)
	 * 
	 * @param challenge
	 *            must be a challenge already contained in the game.
	 * @param challengeState
	 * @return <code>true</code> if an entry was set. <code>false</code>
	 *         otherwise.
	 */
	public boolean setChallengeState(Challenge challenge, ChallengeState challengeState) {
		if (this.challengeStates.containsKey(challenge))
			return false;
		else
			this.challengeStates.put(challenge, challengeState);
		return true;

	}

	/**
	 * @return the challenges states.
	 */
	public ChallengeState getChallengesStates(Challenge c) {
	
		//TODO: Handle null challengeStates
		checkNotNull(challengeStates.get(c));
		return challengeStates.get(c);
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

	/**
	 * @return the reward
	 */
	public Optional<QuizWalkGameReward> getReward() {
		return reward;
	}

}
