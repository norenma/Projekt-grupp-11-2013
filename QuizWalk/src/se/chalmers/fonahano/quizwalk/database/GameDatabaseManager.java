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

	private GameDatabaseHelper helper;

	private GameDatabaseManager(Context ctx) {
		helper = new GameDatabaseHelper(ctx);
	}

	private GameDatabaseHelper getHelper() {
		return helper;
	}

	// - DAO OBJECTS for QUIZWALKGAMES - //

	public List<QuizWalkGame> getAllQuizWalkGame() {
		List<QuizWalkGame> quizWalkGameList = null;
		try {
			quizWalkGameList = getHelper().getQuizWalkDao().queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizWalkGameList;
	}

	public void addQuizWalkGame(QuizWalkGame q) {
		try {
			getHelper().getQuizWalkDao().create(q);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public QuizWalkGame getQuizWalkGameById(int quizWalkId) {
		QuizWalkGame quizWalk = null;
		try {
			quizWalk = getHelper().getQuizWalkDao().queryForId(quizWalkId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizWalk;
	}

	// ///////////////


	public void deleteWishList(QuizWalkGame q) {
		try {
			getHelper().getQuizWalkDao().delete(q);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateQuizWalkGame(QuizWalkGame quizWalkGame) {
		try {
			getHelper().getQuizWalkDao().update(quizWalkGame);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}