package se.chalmers.fonahano.quizwalk.model;

import static com.google.common.base.Preconditions.checkNotNull;
import static se.chalmers.fonahano.quizwalk.utils.Utilities.checkNotNullOrEmpty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import se.chalmers.fonahano.quizwalk.model.Challenge.ChallengeState;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * A game of QuizWalk! This class will contain all information needed to
 * describe a whole set of {@link Challenge}s, including their current
 * {@link Challenge.ChallengeState}.
 * 
 */
@DatabaseTable(tableName = "quizwalkgames")
public class QuizWalkGame extends Game{
	private static final long serialVersionUID = 1L;

	/**
	 * Builder pattern class for QuizWalkGame. "Builder"-class makes it easier
	 * to create a QuizWalkGame. Useful to be called from GUI that lets users to
	 * create a QuizWalkGame. Every method returns the same Builder it was
	 * called upon, enabling "method chaining". Call build() to get a
	 * QuizWalk-object.
	 * 
	 */
	public static class Builder {
		private String name;
		private String description;
		private Optional<Image> image;
		private List<Challenge> challenges;
		private Optional<QuizWalkGameReward> reward;

		/**
		 * Get a new Builder.
		 */
		public Builder() {
			name = "DEFAULT";
			description = "";
			image = Optional.<Image> absent();
			challenges = new ArrayList<Challenge>();
			reward = Optional.<QuizWalkGameReward> absent();

		}

		/**
		 * @param name
		 *            of this Game. COMPULSORY
		 */
		public Builder name(String name) {
			this.name = name;
			return this;
		}

		/**
		 * @param description
		 *            of the Game. COMPULSORY
		 */
		public Builder description(String description) {
			this.description = description;
			return this;
		}

		/**
		 * @param image
		 *            of the Game.
		 */
		public Builder image(Image image) {
			this.image = Optional.fromNullable(image);
			return this;
		}

		/**
		 * @param c
		 *            the Challenge to add to the Game.
		 */
		public Builder addChallenge(Challenge c) {
			this.challenges.add(c);
			return this;
		}

		/**
		 * @param r
		 *            the Reward of the game.
		 */
		public Builder reward(QuizWalkGameReward r) {
			this.reward = Optional.fromNullable(r);
			return this;
		}

		/**
		 * @return the QuizWalkGame that was built. Be sure to have set name,
		 *         description, an preferably some challenges.
		 */
		public QuizWalkGame build() {
			return new QuizWalkGame(name,
				description,
				image,
				challenges,
				reward);
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
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
		public Optional<Image> getImage() {
			return image;
		}

		/**
		 * @return the challenges, MUTABLE.
		 */
		public List<Challenge> getChallenges() {
			return challenges;
		}

		/**
		 * @return the reward
		 */
		public Optional<QuizWalkGameReward> getReward() {
			return reward;
		}
	}

	// Class variables //
	
	//SQL id
	@DatabaseField(generatedId = true)
	private int id;

	/**
	 * Name of this game of QuizWalk.
	 */
	@DatabaseField
	private final String name;

	/**
	 * Description of this QuizWalkGame. Describing the set of challenges
	 * included. Can be empty.
	 */
	@DatabaseField
	private final String description;

	/**
	 * Image representing this Game.
	 */
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private final Optional<Image> image;

	/**
	 * The challenges that constitute this QuizWalk in the order they are to be
	 * presented. More formally, <code>challenges.get(0)</code> represents the
	 * first Challenge - while higher index number indicate later access.
	 * Associated with every challenge in an active game is its current state
	 * defined in {@link #challengeStates}.
	 * 
	 */
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private final ArrayList<Challenge> challenges;

	/**
	 * Keeps track of the {@link ChallengeState}s of this game's
	 * <code>Challenge</code>s. This is done by means of key-value pairs where
	 * keys reflects (points to same object) as the elements of
	 * {@link #challenges}.
	 */
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private final HashMap<Challenge, ChallengeState> challengeStates;

	/**
	 * The reward, if any, granted to user who completes this QuizWalk.
	 */
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private final Optional<QuizWalkGameReward> reward;

	// Needed for ORMLite reflection.
	QuizWalkGame() {
		name = null;
		description = null;
		image = null;
		challenges = null;
		challengeStates = null;
		reward = null;
	}

	/**
	 * Create a new QuizWalkGame. This is a whole description of a game of
	 * QuizWalk - either running or dormant. This constructor sets all
	 * {@link ChallengeState}s to <code>ChallengeState.DEFAULT</code>. Use
	 * start() immediately after calling this constructor if you intend to run
	 * the game.
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

		this.name = checkNotNullOrEmpty(name,
			"name can't be non-empty.");

		this.description = checkNotNull(description);

		this.image = checkNotNull(image);

		this.challenges = new ArrayList<Challenge>(checkNotNullOrEmpty(challenges,
			"list of challenges can't be empty"));

		this.reward = checkNotNull(reward);

		this.challengeStates = new HashMap<Challenge, Challenge.ChallengeState>();

		// Add challenges from list and set them to default.
		for (Challenge c : challenges) {
			challengeStates.put(c,
				ChallengeState.DEFAULT);
		}
	}

	/**
	 * Creates a copy of the provided QuizWalkGame. This constructor will also
	 * copy the ChallengeStates.
	 * 
	 * @param o
	 *            the Game to be copied.
	 */
	public QuizWalkGame(QuizWalkGame o) {
		this.name = o.getName();
		this.description = o.getDescription();
		this.image = o.getImage();
		this.challenges = new ArrayList<Challenge>(o.getChallenges());
		this.reward = o.getReward();
		this.challengeStates = new HashMap<Challenge, Challenge.ChallengeState>();
		for (Challenge c : o.getChallenges()) {
			challengeStates.put(c,
				o.getChallengeStateOf(c));
		}
	}

	/**
	 * Starts game by setting all ChallengeStates to <code>UNVISITED</code>. You
	 * might also want to call this to reset the game.
	 */
	public void start() {
		for (Challenge c : challenges) {
			challengeStates.put(c,
				ChallengeState.UNVISITED);
		}
	}

	/**
	 * Stops game by setting all ChallengeStates to <code>DEFAULT</code>.
	 */
	public void stop() {
		for (Challenge c : challenges) {
			challengeStates.put(c,
				ChallengeState.DEFAULT);
		}
	}

	/**
	 * @return the Challenges of this QuizWalk.
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
			if (challengeStates.get(c)
				.equals(ChallengeState.UNVISITED))
				s = GameState.RUNNING;
		}
		return s;
	}

	/**
	 * Sets a Challenge in this game to a particular {@link GameState}. If you
	 * want to clean ChallengeState data use stop() or start() instead.
	 * 
	 * @param challenge
	 *            must be a challenge already contained in the game.
	 * @param challengeState
	 * @return <code>true</code> if the challenge was on the list and state was
	 *         set. <code>false</code> otherwise.
	 */
	public boolean setChallengeState(Challenge challenge,
			ChallengeState challengeState) {
		if (!challenges.contains(challenge))
			return false;
		else {
			this.challengeStates.put(challenge,
				challengeState);
			return true;
		}

	}

	/**
	 * @return the state of the provided Challenge.
	 */
	public ChallengeState getChallengeStateOf(Challenge c) {

		// TODO: Handle null challengeStates
		return checkNotNull(challengeStates.get(c));
	}

	/**
	 * @return the name of this Game.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Description of this QuizWalkGame. Describing the set of challenges
	 * included. Can be empty.
	 * 
	 * @return the description. Can be empty.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the image representing this game, if available.
	 */
	public Optional<Image> getImage() {
		return image;
	}

	/**
	 * @return The reward, if any. <code>Optional.absent()</code> otherwise.
	 *         Should be granted to user who completes this QuizWalk.
	 */
	public Optional<QuizWalkGameReward> getReward() {
		return reward;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
}
