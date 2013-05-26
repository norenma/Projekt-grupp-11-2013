package se.chalmers.fonahano.quizwalk.presentation;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.ChallengeLocation;
import se.chalmers.fonahano.quizwalk.model.Coordinates;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import se.chalmers.fonahano.quizwalk.presentation.C.Intent.Action;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

/**
 * A singleton class to keep track of active QuizWalkGames, Builders and other
 * objects across the different activities.
 * 
 * Useful to use as an Wrapper of active games (Games started by the player).
 * Store and retrieve data of activated games, last known position and custom
 * location nodes.
 * 
 */
public enum StateSingleton {

	INSTANCE;

	/**
	 * The users current location, or at least the last known position.
	 */
	private Coordinates currentLocation;

	/**
	 * Games that the player is participating in.
	 */
	private QuizWalkGame activeQuizWalk;

	/**
	 * Custom location nodes that user can set up on the map.
	 */
	private final List<ChallengeLocation> nodes;

	private Challenge.Builder currentChallengeBuilder;

	private QuizWalkGame.Builder currentQuizWalkBuilder;

	private StateSingleton() {
		nodes = new ArrayList<ChallengeLocation>();
		currentLocation = null;
		activeQuizWalk = null;
		currentChallengeBuilder = null;
		currentQuizWalkBuilder = null;
	}

	/**
	 * Gets the list of saved nodes. Use <code>addNode()</code> to save nodes.
	 * 
	 * @return a list of previously added custom ChallengeLocation object
	 *         ("node").
	 */
	public ImmutableList<ChallengeLocation> getNodes() {
		return ImmutableList.copyOf(nodes);
	}

	/**
	 * @return the activated QuizWalkGame
	 */
	public Optional<QuizWalkGame> getActiveQuizWalk() {
		return Optional.fromNullable(this.activeQuizWalk);
	}

	/**
	 * @return the last stored currentCoordinate, if available.
	 */
	public Optional<Coordinates> getCurrentCoordinate() {
		return Optional.fromNullable(currentLocation);
	}

	/**
	 * @param node
	 *            the custom location to be removed from map.
	 * @return true, only if map was modified.
	 */
	public boolean removeNode(ChallengeLocation node) {
		return nodes.remove(node);
	}

	/**
	 * @return the currentQuizWalkBuilder
	 */
	public Optional<QuizWalkGame.Builder> getCurrentQuizWalkBuilder() {
		return Optional.fromNullable(currentQuizWalkBuilder);
	}

	/**
	 * @return the currentChallengeBuilder, if available.
	 */
	public Optional<Challenge.Builder> getCurrentChallengeBuilder() {
		return Optional.fromNullable(currentChallengeBuilder);
	}

	/**
	 * @param node
	 *            the custom location to be added to map.
	 * @return true, only if map was modified
	 */
	public boolean addNode(ChallengeLocation node) {
		return nodes.add(checkNotNull(node));
	}

	/**
	 * @param currentLocation
	 *            the location to be saved. null if there is none.
	 * 
	 * @return the key that represents this change.
	 */
	public String setCurrentLocation(ChallengeLocation currentLocation) {
		this.currentLocation = currentLocation;
		return Action.STATE_CHANGED_CURRENT_LOCATION;
	}

	/**
	 * @param currentQuizWalkBuilder
	 *            the currentQuizWalkBuilder to set
	 * @return the key {@link Action#STATE_CHANGED_QUIZWALK_BUILDER} that
	 *         represents this intent.
	 */
	public String setCurrentQuizWalkBuilder(
			QuizWalkGame.Builder currentQuizWalkBuilder) {
		this.currentQuizWalkBuilder = currentQuizWalkBuilder;
		return Action.STATE_CHANGED_QUIZWALK_BUILDER;
	}

	/**
	 * @param currentChallengeBuilder
	 *            the currentChallengeBuilder to set. Set to null to clear.
	 * 
	 * @return the key {@link Action#STATE_CHANGED_CHALLENGE_BUILDER} that
	 *         represents this intent.
	 */
	public String setCurrentChallengeBuilder(
			Challenge.Builder currentChallengeBuilder) {
		this.currentChallengeBuilder = currentChallengeBuilder;
		return Action.STATE_CHANGED_CHALLENGE_BUILDER;
	}

	/**
	 * @param activeQuizWalk
	 *            the activeQuizWalk to set. Set to null to clear.
	 * 
	 * @return the key {@link Action#STATE_CHANGED_ACTIVE_QUIZWALK} that
	 *         represents this intent.
	 */
	public String setActiveQuizWalk(QuizWalkGame activeQuizWalk) {
		this.activeQuizWalk = activeQuizWalk;
		return Action.STATE_CHANGED_ACTIVE_QUIZWALK;
	}

}
