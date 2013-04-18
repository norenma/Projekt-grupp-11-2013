package temp.debug.norenma;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Answer;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Challenge;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.ChallengeReward;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Question;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.QuizWalkGame;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Reward;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.StringAnswer;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.StringQuestion;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.map.Location;

public class TestRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QuizWalkGame quiz=createGame();
		Iterator<Challenge> i=quiz.getChallenges().keySet().iterator();
		while(i.hasNext()){
			playChallenge(i.next());
		}

	}
	
	private static void playChallenge(Challenge c) {
		System.out.println(c.getQuestion().getMedia().toString() + "\n");
		Iterator<Answer> i=c.getListOfAnswers().iterator();
		Scanner sc=new Scanner(System.in);
		while(i.hasNext()){
			System.out.println(i.next().getMedia().toString());
		}
		String s=sc.nextLine();
		if(c.isCorrectAnswer(new StringAnswer(s))){
			System.out.println(c.getReward().getDescription() + "\n");
			System.out.println("---------------------------------\n");
		}
		
	}

	public static Challenge createChallenge1(){
		//Creates a question and answers
		Question question=new StringQuestion("What is the capital of USA?");
		Set<Answer> answers=new HashSet<Answer>();
		answers.add(new StringAnswer("New York"));
		answers.add(new StringAnswer("Washington"));
		answers.add(new StringAnswer("Los Angeles"));
		
		Answer correctAnswer=new StringAnswer("Washington");
		
		//Genereates a position
		Set<Location> locations=new HashSet<Location>();
		locations.add(new Location(100,100,"By the rock", null));
		
		ChallengeReward reward=new ChallengeReward(100, "Good job!", null);
		
		return new Challenge("A question about America..", question, 
				answers, correctAnswer, locations, reward);
	}
	
	public static Challenge createChallenge2(){
		//Creates a question and answers
		Question question=new StringQuestion("Where was the olympic games 1996?");
		Set<Answer> answers=new HashSet<Answer>();
		answers.add(new StringAnswer("Sidney"));
		answers.add(new StringAnswer("London"));
		answers.add(new StringAnswer("Atlanta"));
		
		Answer correctAnswer=new StringAnswer("Atlanta");
		
		//Genereates a position
		Set<Location> locations=new HashSet<Location>();
		locations.add(new Location(100,100,"By the rock", null));
		
		ChallengeReward reward=new ChallengeReward(100, "Sweet!", null);
		
		return new Challenge("A question about OS..", question, 
				answers, correctAnswer, locations, reward);
	}
	
	public static Challenge createChallenge3(){
		//Creates a question and answers
		Question question=new StringQuestion("What is the highest mountain on earth?");
		Set<Answer> answers=new HashSet<Answer>();
		answers.add(new StringAnswer("Month blanc"));
		answers.add(new StringAnswer("K2"));
		answers.add(new StringAnswer("Mount Everest"));
		
		Answer correctAnswer=new StringAnswer("Mount Everest");
		
		//Genereates a position
		Set<Location> locations=new HashSet<Location>();
		locations.add(new Location(100,100,"By the rock", null));
		
		ChallengeReward reward=new ChallengeReward(100, "Awesome!", null);
		
		return new Challenge("A question about Mountains", question, 
				answers, correctAnswer, locations, reward);
	}
	
	public static QuizWalkGame createGame(){
		//Fills a Map with challenges
		Map<Challenge, Challenge.ChallengeState> challenges=new HashMap<Challenge, Challenge.ChallengeState>();
		challenges.put(createChallenge1(), Challenge.ChallengeState.DEFAULT);
		challenges.put(createChallenge2(), Challenge.ChallengeState.DEFAULT);
		challenges.put(createChallenge3(), Challenge.ChallengeState.DEFAULT);
		return new QuizWalkGame("A test-run for QuizWalk", null, challenges);
	}

}
