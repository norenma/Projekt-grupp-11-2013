package temp.debug.tortal;

import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;

public class DebugFactory {

	public static QuizWalkGame getRandomTortalChalmersQuizWalkGame1() {
		QuizWalkGame.Builder builder = new QuizWalkGame.Builder();

		// Challenges
		Challenge c1 = new Challenge.Builder().description("1: Korsningen")
			.addLocation(57.689854d,
				11.978145d)
			.question("What is really cool?")
			.correctAnswer("42")
			.addIncorrectAnswer("Bieber")
			.addIncorrectAnswer("North Korea")
			.addIncorrectAnswer("aina")
			.build();

		Challenge c2 = new Challenge.Builder().description("2: Kiosken")
			.addLocation(57.687107d,11.981138d)
			.question("Vilken film är bäst?")
			.correctAnswer("Mad Max 2: The Road Warrior")
			.addIncorrectAnswer("Avatar")
			.addIncorrectAnswer("Bad Boys 2")
			.addIncorrectAnswer("Fast & Furious 8")
			.build();

		return builder.name("tortal test game 1").description("Two challenges in chalmers").addChallenge(c1).addChallenge(c2).build();
	}
}
