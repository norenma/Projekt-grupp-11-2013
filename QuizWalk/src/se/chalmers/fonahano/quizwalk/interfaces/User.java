package se.chalmers.fonahano.quizwalk.interfaces;

import java.util.ArrayList;

import se.chalmers.fonahano.quizwalk.model.AbstractReward;

public interface User {

	public abstract String getUserName();

	/**
	 * @return the listOfUserRewards
	 */
	public abstract ArrayList<? extends AbstractReward> getListOfUserRewards();

	/**
	 * @return the email
	 */
	public abstract String getEmail();

}