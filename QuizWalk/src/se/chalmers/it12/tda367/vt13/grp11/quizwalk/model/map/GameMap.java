package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.map;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.QuizWalkGame;

/**
 * A <TT>Map</TT> singleton will keep track of active {@link #activeGames} and
 * custom {@link #nodes} local to the client. 
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
	private final Set<Location> nodes;

	private GameMap() {
		this.nodes = new HashSet<Location>();
		this.activeGames = new HashSet<QuizWalkGame>();
		this.currentLocation = Optional.<Location>absent();
	}

	/**
	 * @return the currentLocation, if available.
	 */
	public Optional<Location> getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * @param currentLocation the currentLocation to set
	 */
	public void setCurrentLocation(Optional<Location> currentLocation) {
		this.currentLocation = checkNotNull(currentLocation);
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

	public ImmutableSet<Location> getNodes() {
		return ImmutableSet.copyOf(nodes);
	}

	public ImmutableSet<QuizWalkGame> getQuizGameWalks() {
		return ImmutableSet.copyOf(activeGames);
	}
}
