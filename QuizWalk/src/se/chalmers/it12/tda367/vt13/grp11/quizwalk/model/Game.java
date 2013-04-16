package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

public abstract class Game {
	
	public static enum State{
		RUNNING,
		GAME_OVER;
	}
	
	/**
	 * @return the current state of this Game.
	 */
	abstract public State getState();

	
}
