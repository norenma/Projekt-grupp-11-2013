package temp.debug.norenma;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import se.chalmers.fonahano.quizwalk.interfaces.Answer;
import se.chalmers.fonahano.quizwalk.interfaces.Image;
import se.chalmers.fonahano.quizwalk.interfaces.Question;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.ChallengeLocation;
import se.chalmers.fonahano.quizwalk.model.ChallengeReward;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGameReward;
import se.chalmers.fonahano.quizwalk.model.StringAnswer;
import se.chalmers.fonahano.quizwalk.model.StringQuestion;

import com.google.common.base.Optional;

public class TestRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QuizWalkGame quiz = createGame();
		Iterator<Challenge> i = quiz.getChallenges()
			.iterator();
		while (i.hasNext()) {
			playChallenge(i.next());
		}

	}

	private static void playChallenge(Challenge c) {
//		System.out.println(c.getQuestion()
//			.getAnswer()
//			.toString() + "\n");
		Iterator<Answer<String>> i = c.getSetOfAnswers()
			.iterator();
		Scanner sc = new Scanner(System.in);
		while (i.hasNext()) {
			System.out.println(i.next()
				.getAnswer()
				.toString());
		}
		String s = sc.nextLine();
		if (c.isCorrectAnswer(new StringAnswer(s))) {
			if (c.getReward()
				.isPresent())
				System.out.println(c.getReward()
					.get()
					.getDescription() + "\n");
			System.out.println("---------------------------------\n");
		}

	}

	public static Challenge createChallenge1() {
		// Creates a question and answers
		String question = "What is the capital of USA?";
		Set<Answer<String>> answers = new HashSet<Answer<String>>();
		answers.add(new StringAnswer("New York"));
		answers.add(new StringAnswer("Washington"));
		answers.add(new StringAnswer("Los Angeles"));
		answers.add(new StringAnswer("Boston"));

		String correctAnswer = "Washington";

		// Genereates a position
//		List<ChallengeLocation> locations = new ArrayList<ChallengeLocation>();
//		locations.add(new ChallengeLocation(53.558,
//			9.927,
//			"By the rock",
//			Optional.<Image> absent()));

		ChallengeReward reward = new ChallengeReward(100,
			"Good job!",
			Optional.<Image> absent());

		// return new Challenge("A question about America..",
		// question,
		// answers,
		// correctAnswer,
		// locations,
		// Optional.of(reward));

		return new Challenge.Builder().description("A question about America..")
			.question(question)
			.correctAnswer(correctAnswer)
			.addSetOfIncorrectAnswers(answers)
			.location(53.558d,
				9.927d)
			.challengeReward(reward)
			.build();
	}

	public static Challenge createChallenge2() {
		// Creates a question and answers
		String question = "Where was the olympic games 1996?";
		Set<Answer<String>> answers = new HashSet<Answer<String>>();
		answers.add(new StringAnswer("Sidney"));
		answers.add(new StringAnswer("London"));
		answers.add(new StringAnswer("Atlanta"));
		answers.add(new StringAnswer("Stockholm"));

		Answer<String> correctAnswer = new StringAnswer("Atlanta");

		// Genereates a position
		List<ChallengeLocation> locations = new ArrayList<ChallengeLocation>();
		locations.add(new ChallengeLocation(41.312,
			8.123,
			"By the rock",
			Optional.<Image> absent()));

		ChallengeReward reward = new ChallengeReward(100,
			"Sweet!",
			Optional.<Image> absent());

		return null;
	}

	public static Challenge createChallenge3() {
		// Creates a question and answers
		Question<String> question = new StringQuestion("What is the highest mountain on earth?");
		Set<Answer<String>> answers = new HashSet<Answer<String>>();
		answers.add(new StringAnswer("Month blanc"));
		answers.add(new StringAnswer("K2"));
		answers.add(new StringAnswer("Mount Everest"));
		answers.add(new StringAnswer("Rocky Mountains"));

		Answer<String> correctAnswer = new StringAnswer("Mount Everest");

		// Genereates a position
		List<ChallengeLocation> locations = new ArrayList<ChallengeLocation>();
		locations.add(new ChallengeLocation(53.551,
			9.993,
			"By the rock",
			Optional.<Image> absent()));

		ChallengeReward reward = new ChallengeReward(100,
			"Awesome!",
			Optional.<Image> absent());

//		return new Challenge("A question about Mountains",
//			question,
//			answers,
//			correctAnswer,
//			locations,
//			Optional.of(reward));
		return null;
	}

	public static QuizWalkGame createGame() {
		// Fills a Map with challenges
		List<Challenge> challenges = new ArrayList<Challenge>();
		challenges.add(createChallenge1());
		challenges.add(createChallenge2());
		challenges.add(createChallenge3());
		QuizWalkGameReward reward = new QuizWalkGameReward(15,
			"Awesome!",
			Optional.<Image> absent());

		return new QuizWalkGame("Quiz",
			"A test-run for QuizWalk",
			Optional.<Image> absent(),
			challenges,
			Optional.of(reward));
	}

}
