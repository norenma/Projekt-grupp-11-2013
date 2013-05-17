package se.chalmers.fonahano.quizwalk.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.ArrayList;

import se.chalmers.fonahano.quizwalk.interfaces.Reward;

import com.google.gson.GsonBuilder;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * A <TT>AndroidUser</TT> represents a person using the Application.
 * 
 */
@DatabaseTable
public class AndroidUser implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * a Users different settings.
	 * 
	 */
	public static class UserSettings implements Serializable {
		private static final long serialVersionUID = 1L;

		private boolean soundEnabled;

		public UserSettings() {
			soundEnabled = true;
		}

		public boolean isSoundEnabled() {
			return soundEnabled;
		}
	}

	@DatabaseField(generatedId = true)
	private int id;
	/**
	 * String representation of this user globally.
	 */
	@DatabaseField
	private String userName;

	/**
	 * Local client settings.
	 */
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private UserSettings userSettings;

	/**
	 * Rewards collected by this user.
	 */
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private final ArrayList<AbstractReward> listOfUserRewards;

	/**
	 * Create a user. This represents the actual human user using the game
	 * client.
	 * 
	 * @param userName
	 *            Public user name for this AndroidUser.
	 * @param userSettings
	 *            Personal settings for this user.
	 * @param listOfUserRewards
	 *            Rewards awarded this user.
	 */
	public AndroidUser(String userName, UserSettings userSettings,
			ArrayList<AbstractReward> listOfUserRewards) {

		this.userName = checkNotNull(userName);
		checkArgument(!userName.isEmpty(),
			"userName can't be empty");

		this.userSettings = checkNotNull(userSettings);

		this.listOfUserRewards = checkNotNull(listOfUserRewards);
	}

	/**
	 * Create a user with default settings and no rewards.
	 * 
	 * @param userName
	 *            Public user name for this AndroidUser.
	 */
	public AndroidUser() {
		this("John Doe",
			new UserSettings(),
			new ArrayList<AbstractReward>());

	}

	public final String getUserName() {
		return userName;
	}

	public final void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean addReward(AbstractReward reward) {
		return listOfUserRewards.add(reward);
	}

	public boolean removeReward(Reward reward) {
		return listOfUserRewards.remove(reward);

	}

	public UserSettings getUserSettings() {
		return userSettings;
	}

	public void saveUserSettings(UserSettings userSettings) {
		this.userSettings = userSettings;
	}

	@Override
	public String toString() {
		return new GsonBuilder().setPrettyPrinting()
			.create()
			.toJson(this);
	}
}
