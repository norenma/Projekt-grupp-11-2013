package se.chalmers.fonahano.quizwalk.interfaces;

import java.util.ArrayList;

public interface User {

	public abstract String getUserName();

	/**
	 * @return the listOfUserRewards
	 */
	public abstract ArrayList<? extends Reward> getListOfUserRewards();

	/**
	 * @return the email
	 */
	public abstract String getEmail();

}