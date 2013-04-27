package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

import java.util.List;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.user.User;

/**
 * The local database/storage handler on the client should implement this class.
 * The Android client this will be bridged probably to SQLite..
 * 
 */
public interface LocalStorage {

	public String saveGame(QuizWalkGame g);

	public List<QuizWalkGame> getSavedGames();

	public String saveUser(User u);

	public User getUser();

}
