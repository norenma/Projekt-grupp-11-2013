package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

import java.io.Serializable;

import com.google.common.base.Optional;

public class QuizWalkGameReward extends Reward implements Serializable {

	public QuizWalkGameReward(int score, String description,
			Optional<Image> image) {
		super(score, description, image);
		// TODO Auto-generated constructor stub
	}

}
