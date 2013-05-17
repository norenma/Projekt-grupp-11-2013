package se.chalmers.fonahano.quizwalk.database;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.fonahano.quizwalk.interfaces.C;
import se.chalmers.fonahano.quizwalk.model.AndroidUser;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class GameOrmLiteSQLiteOpenHelper extends OrmLiteSqliteOpenHelper {
	// any time you make changes to your database objects, you may have to increase the database version
	private static final int DATABASE_VERSION = 1;

	// the DAO object we use to access the SimpleData table
	private Dao<QuizWalkGame, Integer> quizWalkDao = null;
	private Dao<AndroidUser, Integer> androidUserDao = null;

	public GameOrmLiteSQLiteOpenHelper(Context context) {
		super(context, C.Data.DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database,ConnectionSource connectionSource) {
		try {
			//Create QuizWalkGame table
			TableUtils.createTable(connectionSource, QuizWalkGame.class);
			//Create AndroidUser table
			TableUtils.createTable(connectionSource, AndroidUser.class);
		} catch (SQLException e) {
			Log.e(GameOrmLiteSQLiteOpenHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db,ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			List<String> allSql = new ArrayList<String>(); 
			switch(oldVersion) 
			{
			  case 1: 
				  //allSql.add("alter table AdData add column `new_col` VARCHAR");
				  //allSql.add("alter table AdData add column `new_col2` VARCHAR");
			}
			for (String sql : allSql) {
				db.execSQL(sql);
			}
		} catch (SQLException e) {
			Log.e(GameOrmLiteSQLiteOpenHelper.class.getName(), "exception during onUpgrade", e);
			throw new RuntimeException(e);
		}
		
	}

	public Dao<QuizWalkGame, Integer> getQuizWalkDao() {
		if (null == quizWalkDao) {
			try {
				quizWalkDao = getDao(QuizWalkGame.class);
			}catch (java.sql.SQLException e) {
				e.printStackTrace();
			}
		}
		return quizWalkDao;
	}
	
	public Dao<AndroidUser, Integer> getAndroidUserDao(){
		if (null == androidUserDao) {
			try {
				androidUserDao = getDao(AndroidUser.class);
			}catch (java.sql.SQLException e) {
				e.printStackTrace();
			}
		}
		return androidUserDao;
	}

}
