package temp.debug.hampus;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Answer;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Challenge;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Challenge.ChallengeState;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.ChallengeReward;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Image;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.map.Location;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.QuizWalkGame;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.StringAnswer;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.StringQuestion;

/*
 * Just a class for testing ideas
 */
public class Testing {
	public static QuizWalkGame testRound;

	public Testing() {
		Challenge c = generateChallenge();
		Map<Challenge, ChallengeState> challenges = new HashMap<Challenge, ChallengeState>();
		challenges.put(c, ChallengeState.DEFAULT);

		testRound = new QuizWalkGame("This is a testround", null,
				challenges);
	}

	public Challenge generateChallenge() {
		Set<Answer> answers = new HashSet<Answer>();
		answers.add(new StringAnswer("Emil"));
		answers.add(new StringAnswer("Hampus"));
		answers.add(new StringAnswer("John"));
		answers.add(new StringAnswer("Kalle"));

		Set<Location> qLocation = new HashSet<Location>();
		qLocation.add(new Location(1, 2, "Testlocation", null));

		Challenge c = new Challenge("testing challenge", new StringQuestion(
				"What's my name?"), answers, new StringAnswer("Hampus"),
				qLocation, new ChallengeReward(10, "Testreward", null));

		return c;

	}

	public static void main(String[] args) {
		Testing t = new Testing();
		System.out.println("Description: " + testRound.getDescription());

		Map<Challenge, ChallengeState> challenges = testRound.getChallenges();

		Set<Challenge> questions = challenges.keySet();

		Iterator<Challenge> it = questions.iterator();
		Challenge c = it.next();
		

		System.out.println(c.getQuestion().getMedia());

		for (Answer answer : c.getListOfAnswers()) {
			System.out.println(answer.getMedia());
		}
		
		Scanner sc = new Scanner(System.in);
		while (true) {
			String input = sc.next();
			if (c.getCorrectAnswer().getMedia().equals(input)) {
				System.out.println("Correct!");
				break;
			} else {
				System.out.println("Wrong!");
			}
		}

	}

}
