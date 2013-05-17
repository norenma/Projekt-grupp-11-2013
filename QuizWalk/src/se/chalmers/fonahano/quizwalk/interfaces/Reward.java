package se.chalmers.fonahano.quizwalk.interfaces;

import com.google.common.base.Optional;

public interface Reward {

	public abstract int getScore();

	public abstract String getDescription();

	public abstract Optional<Image> getImage();

}