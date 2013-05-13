package se.chalmers.fonahano.quizwalk.database;

import java.sql.SQLException;
import java.util.List;

import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import android.content.Context;

public class GameDatabaseManager implements LocalDatabase {

	static private LocalDatabase instance;

	static public void init(Context ctx) {
		if (null == instance) {
			instance = new GameDatabaseManager(ctx);
		}
	}

	static public LocalDatabase getInstance() {
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

	/* (non-Javadoc)
	 * @see se.chalmers.fonahano.quizwalk.database.LocalDatabase#getAllQuizWalkGame()
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see se.chalmers.fonahano.quizwalk.database.LocalDatabase#getQuizWalkGameById(int)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see se.chalmers.fonahano.quizwalk.database.LocalDatabase#addQuizWalkGame(se.chalmers.fonahano.quizwalk.model.QuizWalkGame, boolean)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see se.chalmers.fonahano.quizwalk.database.LocalDatabase#deleteQuizWalkGame(se.chalmers.fonahano.quizwalk.model.QuizWalkGame)
	 */
	@Override
	public void deleteQuizWalkGame(QuizWalkGame q) {
		try {
			getHelper().getQuizWalkDao()
				.delete(q);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see se.chalmers.fonahano.quizwalk.database.LocalDatabase#updateQuizWalkGame(se.chalmers.fonahano.quizwalk.model.QuizWalkGame)
	 */
	@Override
	public void updateQuizWalkGame(QuizWalkGame quizWalkGame) {
		try {
			getHelper().getQuizWalkDao()
				.update(quizWalkGame);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}