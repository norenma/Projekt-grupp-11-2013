package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

/**
 * <code>QuizWalkGame</code> will subclass this.
 * 
 */
public abstract class Game {

	/**
	 * Identifiers for different states of a <code>Game</code>.
	 */
	public static enum GameState {
		/**
		 * The game is ongoing.
		 */
		RUNNING,

		/**
		 * The game has ended.
		 */
		GAME_OVER;
	}

	/**
	 * @return the current {@link GameState} of this game.
	 */
	abstract public GameState getGameState();

}
