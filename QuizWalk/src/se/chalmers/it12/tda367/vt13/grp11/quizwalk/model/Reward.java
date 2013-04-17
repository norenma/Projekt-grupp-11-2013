package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.user.User;

/**
 * A reward is an achievement that a {@link User} can collect.
 * 
 */
public abstract class Reward {

	/**
	 * The value of this reward.
	 */
	private final int score;

	/**
	 * Common description of this reward. Should by default also include
	 * reference to the QuizWalk that it was obtained from.
	 */
	private final String description;

	/**
	 * Image associated with this reward.
	 */
	private final Image image;

	/**
	 * 
	 * Create a new Reward. A reward can be added to a {@link Challenge}.
	 * 
	 * @param score
	 * @param description
	 *            of this reward
	 * @param image
	 *            to be shown in association with this reward
	 * @throws IllegalArgumentException
	 *             if score is negative, or description is empty
	 * @throws NullPointerException
	 *             if any object is null.
	 */
	public Reward(int score, String description, Image image) {

		this.score = score;
		checkArgument(score >= 0, "Score can't be negative");

		this.description = checkNotNull(description);
		checkArgument(!description.isEmpty(), "Description can't be empty");

		this.image = image;
	}

	public int getScore() {
		return score;
	}

	public String getDescription() {
		return description;
	}

	public Image getImage() {
		return image;
	}
}
