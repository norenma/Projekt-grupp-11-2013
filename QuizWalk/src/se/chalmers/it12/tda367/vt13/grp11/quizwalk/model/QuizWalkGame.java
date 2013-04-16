package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Challenge.ChallengeState;

public class QuizWalkGame extends Game {

	private final String description;
	private final Image image;
	private final java.util.Map<Challenge, ChallengeState> challenges;

	/**
	 * @param description
	 * @param image
	 * @param challenges
	 */
	public QuizWalkGame(String description, Image image,
			java.util.Map<Challenge, ChallengeState> challenges) {
		this.description = description;
		this.image = image;
		this.challenges = challenges;
	}

	/**
	 * Returns GAME_OVER only if all challenges have either been encountered,
	 * ignored or in some other way not presented. More formally, if at least
	 * one challenge in this game is <TT>Challenge.State.UNVISITED</TT> then the
	 * game is stil running.
	 */
	@Override
	public State getState() {

		State s = State.GAME_OVER;

		for (Challenge c : challenges.keySet()) {
			if (challenges.get(c).equals(ChallengeState.UNVISITED)) {
				s = State.RUNNING;
			}
		}

		return s;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @return the challenges
	 */
	public java.util.Map<Challenge, ChallengeState> getChallenges() {
		return challenges;
	}
}
