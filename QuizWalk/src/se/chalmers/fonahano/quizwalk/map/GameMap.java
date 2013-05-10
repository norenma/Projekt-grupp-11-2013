package se.chalmers.fonahano.quizwalk.map;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

/**
 * A singleton class to keep track of active QuizWalkGames and custom nodes
 * local to the client.
 * 
 * Useful to use as an Wrapper of active games (Games started by the player).
 * Store and retrieve data of activated games, last known position and custom
 * location nodes.
 * 
 */
public enum GameMap {

	INSTANCE;

	/**
	 * The users current location, or at least the last known position.
	 */
	private Optional<Location> currentLocation;

	/**
	 * Games that the player is participating in.
	 */
	private final Set<QuizWalkGame> activeGames;

	/**
	 * Custom location nodes that user can set up on the map.
	 */
	private final List<Location> nodes;

	private GameMap() {
		this.nodes = new ArrayList<Location>();
		this.activeGames = new HashSet<QuizWalkGame>();
		this.currentLocation = Optional.<Location> absent();
	}

	// TODO: Debug methods to get all nodes. I don't like arrays but since we
	// don't want to let View (mVc) to modify our list we'll do this way
	// now.
	// Check Google Guava on Immutable Collections.
	// https://google-collections.googlecode.com/svn/trunk/javadoc/com/google/common/collect/ImmutableList.html

	/**
	 * Gets the list of saved nodes. Use <code>addNode()</code> to save nodes.
	 * 
	 * @return a list of previously added custom Location object ("node").
	 */
	public ImmutableList<Location> getNodes() {
		return ImmutableList.copyOf(nodes);
	}

	/**
	 * @return a set of previously added QuizWalkGames. Use addGame() to add a
	 *         Game to the set.
	 */
	public ImmutableSet<QuizWalkGame> getQuizGameWalks() {
		return ImmutableSet.copyOf(activeGames);
	}

	/**
	 * @param currentLocation
	 *            the location to be saved.
	 */
	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = Optional.fromNullable(currentLocation);
	}

	/**
	 * The last saved location. Will return Optional.absent() if current
	 * location never been saved or it was set to null.
	 * 
	 * @return the currentLocation, if available.
	 */
	public Optional<Location> getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * @param node
	 *            the custom location to be added to map.
	 * @return true, only if map was modified
	 */
	public boolean addNode(Location node) {
		return nodes.add(checkNotNull(node));
	}

	/**
	 * @param node
	 *            the custom location to be removed from map.
	 * @return true, only if map was modified
	 */
	public boolean removeNode(Location node) {
		return nodes.remove(node);
	}

	/**
	 * @param game
	 *            that will supply locations to be marked on the map
	 * @return true, only if map was modified
	 */
	public boolean addGame(QuizWalkGame game) {
		return activeGames.add(checkNotNull(game));
	}

	/**
	 * @param game
	 *            locations to be removed from map
	 * @return true, only if map was modified
	 */
	public boolean removeGame(QuizWalkGame game) {
		return activeGames.remove(game);
	}

	// TODO: Debug methods to get all nodes. I don't like arrays but since we
	// don't want to let View (mVc) to modify our list we'll do this way
	// now.
	// Check Google Guava on Immutable Collections.
	// https://google-collections.googlecode.com/svn/trunk/javadoc/com/google/common/collect/ImmutableList.html

}
