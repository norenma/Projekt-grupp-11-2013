package se.chalmers.fonahano.quizwalk.model;

import se.chalmers.fonahano.quizwalk.interfaces.Image;

import com.google.common.base.Optional;

/**
 * A {@link AbstractReward} that can be included in a {@link Challenge}. Would
 * be typically granted the user for have completing that <TT>Challenge</TT>
 * 
 */
public class ChallengeReward extends AbstractReward {
	private static final long serialVersionUID = 1L;

	public ChallengeReward(int score, String description, Optional<Image> image) {
		super(score, description, image);
	}

}
