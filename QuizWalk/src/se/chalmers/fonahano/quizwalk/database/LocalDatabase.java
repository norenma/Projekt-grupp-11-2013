package se.chalmers.fonahano.quizwalk.database;

import java.util.List;

import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import se.chalmers.fonahano.quizwalk.model.QuizWalkUser;

public interface LocalDatabase {

	public abstract List<QuizWalkGame> getAllQuizWalkGame();

	/**
	 * Get a QuizWalkGame by its ID in the database.
	 * 
	 * @param quizWalkId
	 * @return The
	 */
	public abstract QuizWalkGame getQuizWalkGameById(int quizWalkId);

	/**
	 * Add a QuizWalkGame to the database.
	 */
	public abstract void addQuizWalkGame(QuizWalkGame q);

	/**
	 * Delete the QuizWalkGame from the database.
	 */
	public abstract void deleteQuizWalkGame(QuizWalkGame q);

	/**
	 * This method is only used when starting the application for the very first
	 * time.
	 */
	public abstract void createUser(QuizWalkUser u);

	/**
	 * @param u
	 *            updates the unique user (id=1).
	 */
	public abstract void updateUser(QuizWalkUser u);

	/**
	 * @return the unique user (id=1)
	 */
	public abstract QuizWalkUser getUser();

}