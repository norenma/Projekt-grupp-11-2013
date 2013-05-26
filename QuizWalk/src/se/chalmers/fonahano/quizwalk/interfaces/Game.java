package se.chalmers.fonahano.quizwalk.interfaces;

import java.io.Serializable;

/**
 * <code>QuizWalkGame</code> will subclass this.
 * 
 */
public interface Game extends Serializable {

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
