package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.map;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashSet;
import java.util.Set;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.QuizWalkGame;

/**
 * A <TT>Map</TT> object will keep track of active {@link #activeGames} and
 * custom {@link #nodes} local to the client.
 * 
 */
public class GameMap {

	/**
	 * Custom location nodes that user can set up on the map.
	 */
	private final Set<Location> nodes;

	/**
	 * Games that the player is participating in.
	 */
	private final Set<QuizWalkGame> activeGames;

	/**
	 * The users current location, or at least the last known position.
	 */
	private final Location currentLocation;

	public GameMap() {
		this.nodes = new HashSet<Location>();
		this.activeGames = new HashSet<QuizWalkGame>();
		this.currentLocation = null;
	}

	public GameMap(Set<Location> nodes, Set<QuizWalkGame> activeGames,
			Location currentLocation) {

		this.nodes = checkNotNull(nodes);
		this.activeGames = checkNotNull(activeGames);
		this.currentLocation = currentLocation;
	}

	/**
	 * @param node
	 *            the custom location to be added to map.
	 * @return true, only if map was modified
	 */
	public boolean addNode(Location node) {
		return nodes.add(node);
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
		return activeGames.add(game);
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
	// don't want to let implementation to modify our list we'll do this way
	// now.
	// Check Google Guava on Immutable Collections.
	// https://google-collections.googlecode.com/svn/trunk/javadoc/com/google/common/collect/ImmutableList.html

	public Location[] getNodes() {
		return (Location[]) nodes.toArray();
	}

	public QuizWalkGame[] getQuizGameWalks() {
		return (QuizWalkGame[]) activeGames.toArray();
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}
}
