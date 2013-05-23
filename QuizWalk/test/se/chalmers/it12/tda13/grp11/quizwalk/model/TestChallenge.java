package se.chalmers.it12.tda13.grp11.quizwalk.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Optional;

import se.chalmers.fonahano.quizwalk.interfaces.Image;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.ChallengeReward;
import se.chalmers.fonahano.quizwalk.model.StringAnswer;

public class TestChallenge {
	
	private Challenge c;

	@Before
	public void setUp() throws Exception {
		c=new Challenge.Builder().addIncorrectAnswer("3").correctAnswer("4").question("?").build();
	}

	@Test
	public void testBuilder(){
		Challenge cTmp=new Challenge.Builder().correctAnswer("correct")
				.challengeReward(new ChallengeReward(50, "Good job!", Optional.<Image> absent()))
				.question("?").build();
		assertTrue(cTmp.getQuestion().toString().equals("?"));
		assertTrue(cTmp.getCorrectAnswer().toString().equals("correct"));
		assertTrue(cTmp.getReward().get().getScore() == 50);
		
		//Tests second constructor of builder, that make a build of a QuizWalk
	}
	
	@Test
	public void testIsCorrectAnswer() {
		assertTrue(c.isCorrectAnswer(new StringAnswer("4")));
	}

}
