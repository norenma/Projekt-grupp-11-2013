package se.chalmers.fonahano.quizwalk.model;

import java.util.List;

import se.chalmers.fonahano.quizwalk.user.User;

/**
 * The local database/storage handler on the client should implement this class.
 * The Android client this will be bridged probably to SQLite..
 * 
 */
public interface LocalStorage  {

	public String saveGame(QuizWalkGame g);

	public List<QuizWalkGame> getSavedGames();

	public String saveUser(User u);

	public User getUser();

}
