package se.chalmers.fonahano.quizwalk.database;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import se.chalmers.fonahano.quizwalk.model.QuizWalkUser;
import se.chalmers.fonahano.quizwalk.presentation.C;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class GameOrmLiteSQLiteOpenHelper extends OrmLiteSqliteOpenHelper {
	private final String CLASS_NAME = getClass().getName();

	/**
	 * To be incremented so that onUpgrade(...) will know when changes are made.
	 */
	private static final int DATABASE_VERSION = 1;

	// the DAO object we use to access the SimpleData table
	private Dao<QuizWalkGame, Integer> quizWalkDao = null;
	private Dao<QuizWalkUser, Integer> androidUserDao = null;

	public GameOrmLiteSQLiteOpenHelper(Context context) {
		super(context, C.Data.DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database,
			ConnectionSource connectionSource) {
		try {

			// QuizWalkGame
			TableUtils.createTable(connectionSource, QuizWalkGame.class);

			// QuizWalkUser
			TableUtils.createTable(connectionSource, QuizWalkUser.class);
		} catch (SQLException e) {
			Log.e(CLASS_NAME, "Exception when creating tables", e);
			throw new RuntimeException(e);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		try {
			List<String> allSql = new ArrayList<String>();
			switch (oldVersion) {
			case 1:
				// allSql.add("alter table AdData add column `new_col` VARCHAR");
				// allSql.add("alter table AdData add column `new_col2` VARCHAR");
			}
			for (String sql : allSql) {
				db.execSQL(sql);
			}
		} catch (SQLException e) {
			Log.e(CLASS_NAME, "exception during onUpgrade", e);
			throw new RuntimeException(e);
		}

	}

	public Dao<QuizWalkGame, Integer> getQuizWalkDao() {
		if (null == quizWalkDao) {
			try {
				quizWalkDao = getDao(QuizWalkGame.class);
			} catch (java.sql.SQLException e) {
				e.printStackTrace();
			}
		}
		return quizWalkDao;
	}

	public Dao<QuizWalkUser, Integer> getAndroidUserDao() {
		if (null == androidUserDao) {
			try {
				androidUserDao = getDao(QuizWalkUser.class);
			} catch (java.sql.SQLException e) {
				e.printStackTrace();
			}
		}
		return androidUserDao;
	}

}
