package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;

public class User {

	/**
	 * String representation of this user globally.
	 */
	private String userName;
	
	/**
	 * Local client settings.
	 */
	private Setting userSettings;
	
	/**
	 * Rewards collected by this user.
	 */
	private final ArrayList<Reward> listOfUserRewards;

	// TODO: Incomplete constructor
	public User(String userName) {
		this.userName = checkNotNull(userName);
		if (userName.isEmpty()) throw new IllegalArgumentException("User name can't be empty");
		this.userSettings = new Setting();
		this.listOfUserRewards = new ArrayList<Reward>();
	}

	public boolean addReward(Reward reward) {
		return listOfUserRewards.add(reward);
	}

	public boolean removeReward(Reward reward) {
		return listOfUserRewards.remove(reward);

	}

	public final String getUserName() {
		return userName;
	}

	public final void setUserName(String userName) {
		this.userName = userName;
	}

	public Setting getUserSettings() {
		return userSettings;
	}

	public void setUserSettings(Setting userSettings) {
		this.userSettings = userSettings;
	}
}
