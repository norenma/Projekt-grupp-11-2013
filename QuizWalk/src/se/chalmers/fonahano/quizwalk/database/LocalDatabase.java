package se.chalmers.fonahano.quizwalk.database;

import java.util.List;

import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;

public interface LocalDatabase {

	public abstract List<QuizWalkGame> getAllQuizWalkGame();

	public abstract QuizWalkGame getQuizWalkGameById(int quizWalkId);

	/**
	 * @param q
	 * @param createIfNotExists
	 *            set this to true to ensure that the item does now exist before
	 *            it is persisted. (If it does exist and this is set to false
	 *            there will be error.) Use update methods to update entries.
	 */
	public abstract void addQuizWalkGame(QuizWalkGame q,
			boolean createIfNotExists);

	public abstract void deleteQuizWalkGame(QuizWalkGame q);

	public abstract void updateQuizWalkGame(QuizWalkGame quizWalkGame);

}