package se.chalmers.it12.tda13.grp11.quizwalk.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import se.chalmers.fonahano.quizwalk.model.StringAnswer;

public class TestQuizWalkActivity {

	private QuizWalkGame qwg;
	
	@Before
	public void setUp() throws Exception {
		qwg=new QuizWalkGame.Builder().addChallenge(new Challenge.Builder().correctAnswer("correct").question("?").build()).build();
	}

	@Test
	public void testBuilder() {
		assertTrue(qwg.getChallenges().get(0).isCorrectAnswer(new StringAnswer("correct")));
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
		
	}

}
