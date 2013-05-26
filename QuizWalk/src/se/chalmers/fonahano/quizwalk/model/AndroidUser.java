package se.chalmers.fonahano.quizwalk.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import se.chalmers.fonahano.quizwalk.interfaces.Reward;
import se.chalmers.fonahano.quizwalk.interfaces.User;

import com.google.gson.GsonBuilder;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * A <TT>AndroidUser</TT> represents a person using the Application.
 * 
 */
@DatabaseTable
public class AndroidUser implements Serializable, User {
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_USERNAME = "John Doe";

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

		public boolean setSoundEnabled(boolean soundEnabled) {
			return soundEnabled == (this.soundEnabled = soundEnabled);
		}
	}

	@DatabaseField(id=true)
	private int id = 1;
	/**
	 * String representation of this user globally.
	 */
	@DatabaseField
	private String userName;

	@DatabaseField
	private String email;

	@DatabaseField(dataType = DataType.BYTE_ARRAY)
	private byte[] password;

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
	 * client. See the no-args constructor for default values.
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
		checkArgument(!userName.isEmpty(), "userName can't be empty");

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
		this(DEFAULT_USERNAME, new UserSettings(),
				new ArrayList<AbstractReward>());

	}

	/* (non-Javadoc)
	 * @see se.chalmers.fonahano.quizwalk.model.User#getUserName()
	 */
	@Override
	public final String getUserName() {
		return userName;
	}

	public final void setUserName(String userName) {
		this.userName = userName;
	}

	/* (non-Javadoc)
	 * @see se.chalmers.fonahano.quizwalk.model.User#getListOfUserRewards()
	 */
	@Override
	public ArrayList<? extends AbstractReward> getListOfUserRewards() {
		return listOfUserRewards;
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
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = checkNotNull(email);
	}

	/**
	 * @return True, only if the passwords MD5-hash equals the MD5 of the saved
	 *         one.
	 */
	public boolean isCorrectPassword(String pw) {

		MessageDigest digester = null;
		byte[] bytesOfPassword = null;

		try {
			digester = MessageDigest.getInstance("MD5");
			bytesOfPassword = pw.getBytes("UTF-8");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

		byte[] hashedInput = digester.digest(bytesOfPassword);

		return Arrays.equals(hashedInput, this.password);

	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		MessageDigest digester = null;
		byte[] bytesOfPassword = null;

		try {
			digester = MessageDigest.getInstance("MD5");
			bytesOfPassword = password.getBytes("UTF-8");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

		byte[] hashedPassword = digester.digest(bytesOfPassword);
		this.password = hashedPassword;
	}

	@Override
	public String toString() {
		return new GsonBuilder().setPrettyPrinting().create().toJson(this);
	}
}
