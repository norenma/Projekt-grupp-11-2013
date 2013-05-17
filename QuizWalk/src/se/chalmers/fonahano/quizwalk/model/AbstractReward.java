package se.chalmers.fonahano.quizwalk.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static se.chalmers.fonahano.quizwalk.model.Utilities.checkNotNullOrEmpty;

import java.io.Serializable;

import se.chalmers.fonahano.quizwalk.interfaces.Image;
import se.chalmers.fonahano.quizwalk.interfaces.Reward;

import com.google.common.base.Optional;

/**
 * A reward is an achievement that a {@link AndroidUser} can collect.
 * 
 */
public abstract class AbstractReward implements Serializable, Reward {
	private static final long serialVersionUID = 1L;

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
	 * Create a new AbstractReward. A reward can be added to a {@link Challenge}
	 * .
	 * 
	 * @param score
	 *            is a numerical value representing this reward. Can't be
	 *            negative.
	 * @param description
	 *            of this reward
	 * @param image
	 *            to represent this AbstractReward, if any.
	 * @throws IllegalArgumentException
	 *             if score is negative, or description is empty
	 * @throws NullPointerException
	 *             if any object is null.
	 */
	public AbstractReward(int score, String description, Optional<Image> image) {

		this.score = score;
		checkArgument(score >= 0,
			"Score can't be negative");

		this.description = checkNotNullOrEmpty(description,
			"Description can't be empty");

		this.image = checkNotNull(image);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.chalmers.fonahano.quizwalk.model.Reward#getScore()
	 */
	@Override
	public int getScore() {
		return score;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.chalmers.fonahano.quizwalk.model.Reward#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.chalmers.fonahano.quizwalk.model.Reward#getImage()
	 */
	@Override
	public Optional<Image> getImage() {
		return image;
	}

}
