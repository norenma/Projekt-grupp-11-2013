package se.chalmers.fonahano.quizwalk.model;

import se.chalmers.fonahano.quizwalk.interfaces.Image;

import com.google.common.base.Optional;

public class QuizWalkGameReward extends Reward_ {
	private static final long serialVersionUID = 1L;

	public QuizWalkGameReward(int score, String description,
			Optional<Image> image) {
		super(score, description, image);
		// TODO Auto-generated constructor stub
	}

}
