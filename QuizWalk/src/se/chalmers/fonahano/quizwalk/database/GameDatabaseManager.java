package se.chalmers.fonahano.quizwalk.database;

import java.sql.SQLException;
import java.util.List;

import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import android.content.Context;

public class GameDatabaseManager {

	static private GameDatabaseManager instance;

	static public void init(Context ctx) {
		if (null == instance) {
			instance = new GameDatabaseManager(ctx);
		}
	}

	static public GameDatabaseManager getInstance() {
		return instance;
	}

	private GameOrmLiteSQLiteOpenHelper helper;

	private GameDatabaseManager(Context ctx) {
		helper = new GameOrmLiteSQLiteOpenHelper(ctx);
	}

	private GameOrmLiteSQLiteOpenHelper getHelper() {
		return helper;
	}

	// - RETRIEVE-QUERIES - //

	public List<QuizWalkGame> getAllQuizWalkGame() {
		List<QuizWalkGame> quizWalkGameList = null;
		try {
			quizWalkGameList = getHelper().getQuizWalkDao()
				.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizWalkGameList;
	}

	public QuizWalkGame getQuizWalkGameById(int quizWalkId) {
		QuizWalkGame quizWalk = null;
		try {
			quizWalk = getHelper().getQuizWalkDao()
				.queryForId(quizWalkId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizWalk;
	}

	/**
	 * @param q
	 * @param createIfNotExists
	 *            set this to true to ensure that the item does now exist before
	 *            it is persisted. (If it does exist and this is set to false
	 *            there will be error.) Use update methods to update entries.
	 */
	public void addQuizWalkGame(QuizWalkGame q, boolean createIfNotExists) {
		try {
			if (createIfNotExists) {
				getHelper().getQuizWalkDao()
					.createIfNotExists(q);
			} else {
				getHelper().getQuizWalkDao()
					.create(q);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	

	// ///////////////

	public void deleteQuizWalkGame(QuizWalkGame q) {
		try {
			getHelper().getQuizWalkDao()
				.delete(q);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateQuizWalkGame(QuizWalkGame quizWalkGame) {
		try {
			getHelper().getQuizWalkDao()
				.update(quizWalkGame);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}