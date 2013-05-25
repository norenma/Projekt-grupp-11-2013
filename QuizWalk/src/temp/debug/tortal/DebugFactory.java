package temp.debug.tortal;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.fonahano.quizwalk.database.GameDatabaseManager;
import se.chalmers.fonahano.quizwalk.database.LocalDatabase;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;

public class DebugFactory {

	public static void addRandomListOfQuizWalksToDB(){
		List<QuizWalkGame> list = new ArrayList<QuizWalkGame>();
		
		list.add(getRandomTortalChalmersQuizWalkGame1());
		list.add(getRandomTortalChalmersQuizWalkGame2());
		list.add(getRandomTortalChalmersQuizWalkGame3());
		
		LocalDatabase db = GameDatabaseManager.getInstance();
		for(QuizWalkGame g : list){
			db.addQuizWalkGame(g);
		}
	}
	public static QuizWalkGame getRandomTortalChalmersQuizWalkGame1() {
		QuizWalkGame.Builder builder = new QuizWalkGame.Builder();

		// Challenges
		Challenge c1 = new Challenge.Builder().description("1: Korsningen")
			.location(57.690909,11.976294)
			.question("What is really cool?")
			.correctAnswer("42")
			.addIncorrectAnswer("Bieber")
			.addIncorrectAnswer("North Korea")
			.addIncorrectAnswer("aina")
			.build();

		Challenge c2 = new Challenge.Builder().description("2: Kiosken")
			.location(57.687121,
				11.981823)
			.question("Vilken film �r b�st?")
			.correctAnswer("Mad Max 2: The Road Warrior")
			.addIncorrectAnswer("Avatar")
			.addIncorrectAnswer("Bad Boys 2")
			.addIncorrectAnswer("Fast & Furious 8")
			.build();

		// Return QuizWalkGame
		return builder.name("tortal test game :: "
				+ (int) (1 + Math.random() * 99))
			.description("Two challenges in chalmers")
			.addChallenge(c1)
			.addChallenge(c2)
			.build();
	}
	
	public static QuizWalkGame getRandomTortalChalmersQuizWalkGame2() {
		QuizWalkGame.Builder builder = new QuizWalkGame.Builder();

		// Challenges
		Challenge c1 = new Challenge.Builder().description("1: Korsningen")
			.location(57.694012,11.970551)
			.question("What is really cool?")
			.correctAnswer("42")
			.addIncorrectAnswer("Bieber")
			.addIncorrectAnswer("North Korea")
			.addIncorrectAnswer("aina")
			.build();
		

		Challenge c2 = new Challenge.Builder().description("2: Kiosken")
			.location(57.690551,11.938823)
			.question("Vilken film �r b�st?")
			.correctAnswer("Mad Max 2: The Road Warrior")
			.addIncorrectAnswer("Avatar")
			.addIncorrectAnswer("Bad Boys 2")
			.addIncorrectAnswer("Fast & Furious 8")
			.build();
		
		Challenge c3 = new Challenge.Builder().description("2: Kiosken")
				.location(57.690932,11.976212)
				.question("What's the capitol of The United States?")
				.correctAnswer("Washington")
				.addIncorrectAnswer("Boston")
				.addIncorrectAnswer("New York")
				.addIncorrectAnswer("Chicago")
				.build();

		// Return QuizWalkGame
		return builder.name("tortal test game :: "
				+ (int) (1 + Math.random() * 99))
			.description("Two challenges in chalmers(2)")
			.addChallenge(c1)
			.addChallenge(c2)
			.addChallenge(c3)
			.build();
	}
	
	public static QuizWalkGame getRandomTortalChalmersQuizWalkGame3() {
		QuizWalkGame.Builder builder = new QuizWalkGame.Builder();

		// Challenges
		Challenge c1 = new Challenge.Builder().description("1: Korsningen")
			.location(57.690095,11.977388)
			.question("What is really cool?")
			.correctAnswer("42")
			.addIncorrectAnswer("Bieber")
			.addIncorrectAnswer("North Korea")
			.addIncorrectAnswer("aina")
			.build();

		Challenge c2 = new Challenge.Builder().description("2: Kiosken")
			.location(57.619854,
					11.908145)
			.question("What's the capitol of The United States?")
			.correctAnswer("Washington")
			.addIncorrectAnswer("Boston")
			.addIncorrectAnswer("New York")
			.addIncorrectAnswer("Chicago")
			.build();

		// Return QuizWalkGame
		return builder.name("tortal test game :: "
				+ (int) (1 + Math.random() * 99))
			.description("Two challenges in chalmers(3)")
			.addChallenge(c1)
			.addChallenge(c2)
			.build();
	}

}



