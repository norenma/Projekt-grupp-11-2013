package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.user;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.fonahano.quizwalk.model.Reward;

/**
 * A <TT>User</TT> represents a person using the Application.
 * 
 */
public class User {

	/**
	 * String representation of this user globally.
	 */
	private String userName;

	/**
	 * Local client settings.
	 */
	private UserSettings userSettings;

	/**
	 * Rewards collected by this user.
	 */
	private final List<Reward> listOfUserRewards;

	/**
	 * Create a user. This represents the actual human user using the game
	 * client.
	 * 
	 * @param userName
	 *            Public user name for this User.
	 * @param userSettings
	 *            Personal settings for this user.
	 * @param listOfUserRewards
	 *            Rewards awarded this user.
	 */
	public User(String userName, UserSettings userSettings,
			List<Reward> listOfUserRewards) {

		this.userName = checkNotNull(userName);
		checkArgument(!userName.isEmpty(), "userName can't be empty");

		this.userSettings = checkNotNull(userSettings);

		this.listOfUserRewards = checkNotNull(listOfUserRewards);
	}

	/**
	 * Create a user with default settings and no rewards.
	 * 
	 * @param userName
	 *            Public user name for this User.
	 */
	public User(String userName) {
		this(userName, new UserSettings(), new ArrayList<Reward>());

	}

	public final String getUserName() {
		return userName;
	}

	public final void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean addReward(Reward reward) {
		return listOfUserRewards.add(reward);
	}

	public boolean removeReward(Reward reward) {
		return listOfUserRewards.remove(reward);

	}

	public UserSettings getUserSettings() {
		return userSettings;
	}

	public void setUserSettings(UserSettings userSettings) {
		this.userSettings = userSettings;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [getUserName()=");
		builder.append(getUserName());
		builder.append(", getUserSettings()=");
		builder.append(getUserSettings());
		builder.append("]");
		return builder.toString();
	}
}
