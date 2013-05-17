package se.chalmers.fonahano.quizwalk.database;

import java.sql.SQLException;
import java.util.List;

import se.chalmers.fonahano.quizwalk.model.AndroidUser;
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

	@Override
	public void addQuizWalkGame(QuizWalkGame q) {
		try {
			getHelper().getQuizWalkDao()
				.create(q);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteQuizWalkGame(QuizWalkGame q) {
		try {
			getHelper().getQuizWalkDao()
				.delete(q);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createUser(AndroidUser u) {
		try {
			getHelper().getAndroidUserDao()
				.create(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateUser(AndroidUser u) {
		try {
			getHelper().getAndroidUserDao()
				.update(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public AndroidUser getUser() {
		AndroidUser u = null;
		try {
			u = getHelper().getAndroidUserDao()
				.queryForId(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	// @Override
	// public void updateQuizWalkGame(QuizWalkGame quizWalkGame) {
	// try {
	// getHelper().getQuizWalkDao()
	// .update(quizWalkGame);
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }

}