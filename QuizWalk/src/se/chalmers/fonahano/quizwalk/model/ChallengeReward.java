package se.chalmers.fonahano.quizwalk.model;

import com.google.common.base.Optional;

/**
 * A {@link Reward} that can be included in a {@link Challenge}. Would be
 * typically granted the user for have completing that <TT>Challenge</TT>
 * 
 */
public class ChallengeReward extends Reward {
	private static final long serialVersionUID = 1L;

	public ChallengeReward(int score, String description, Optional<Image> image) {
		super(score, description, image);
		// TODO Auto-generated constructor stub
	}

}
