package se.chalmers.fonahano.quizwalk.database;

import java.util.List;

import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;

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
	

}