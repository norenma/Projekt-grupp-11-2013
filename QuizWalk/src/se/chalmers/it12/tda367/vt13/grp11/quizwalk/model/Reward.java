package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.utils.Utilities.checkNotNullOrEmpty;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.user.User;

import com.google.common.base.Optional;

/**
 * A reward is an achievement that a {@link User} can collect.
 * 
 */
public class Reward {

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
	private final Optional<Image> image;

	/**
	 * 
	 * Create a new Reward. A reward can be added to a {@link Challenge}.
	 * 
	 * @param score
	 *            is a numerical value representing this reward. Can't be
	 *            negative.
	 * @param description
	 *            of this reward
	 * @param image
	 *            to represent this Reward, if any.
	 * @throws IllegalArgumentException
	 *             if score is negative, or description is empty
	 * @throws NullPointerException
	 *             if any object is null.
	 */
	public Reward(int score, String description, Optional<Image> image) {

		this.score = score;
		checkArgument(score >= 0, "Score can't be negative");

		this.description = checkNotNullOrEmpty(description,
				"Description can't be empty");

		this.image = checkNotNull(image);
	}

	public int getScore() {
		return score;
	}

	public String getDescription() {
		return description;
	}

	public Optional<Image> getImage() {
		return image;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Reward [getScore()=");
		builder.append(getScore());
		builder.append(", getDescription()=");
		builder.append(getDescription());
		builder.append(", getImage()=");
		builder.append(getImage());
		builder.append("]");
		return builder.toString();
	}
}
