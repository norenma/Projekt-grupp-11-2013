package se.chalmers.it12.tda13.grp11.quizwalk.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import se.chalmers.fonahano.quizwalk.interfaces.Image;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.ChallengeReward;
import se.chalmers.fonahano.quizwalk.model.Coordinates;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame.ChallengeState;
import se.chalmers.fonahano.quizwalk.model.StringAnswer;

import com.google.common.base.Optional;

public class TestQuizWalk {

	private QuizWalkGame qwg;

	@Before
	public void setUp() throws Exception {
		Challenge c1 = new Challenge.Builder()
				.correctAnswer("correct")
				.question("1")
				.challengeReward(
						new ChallengeReward(50, " ", Optional.<Image> absent()))
				.location(12.3, 12.3).build();
		Challenge c2 = new Challenge.Builder()
				.correctAnswer("correct")
				.question("2")
				.challengeReward(
						new ChallengeReward(30, " ", Optional.<Image> absent()))
				.location(32.1, 32.1).build();
		qwg = new QuizWalkGame.Builder().addChallenge(c1).addChallenge(c2)
				.build();

		// Sets one challenge to failed and one to complete,
		// user should get 50 points
		qwg.setChallengeState(c1, ChallengeState.COMPLETED);
		qwg.setChallengeState(c2, ChallengeState.FAILED);
	}

	@Test
	public void testBuilder() {
		assertTrue(qwg.getChallenges().get(0)
				.isCorrectAnswer(new StringAnswer("correct")));
	}

	@Test
	public void testBuilderQuizWalkGame() {

	}

	@Test
	public void testStart() {

	}

	@Test
	public void testStop() {

	}

	@Test
	public void testGetName() {

	}

	@Test
	public void testGetChallenge() {
		assertTrue(qwg.getChallenge(new Coordinates(32.1, 32.1)).getQuestion().toString()
				.equals("1"));

	}

	@Test
	public void testGetCurrentScore() {
		assertTrue(qwg.getCurrentScore() == 50);
	}

}
