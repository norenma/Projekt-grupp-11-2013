package temp.debug.norenma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Answer;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Challenge;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.ChallengeReward;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Image;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Question;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.QuizWalkGame;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.QuizWalkGameReward;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.StringAnswer;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.StringQuestion;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.map.Location;

import com.google.common.base.Optional;

public class TestRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QuizWalkGame quiz=createGame();
		Iterator<Challenge> i=quiz.getChallenges().iterator();
		while(i.hasNext()){
			playChallenge(i.next());
		}

	}
	
	private static void playChallenge(Challenge c) {
		System.out.println(c.getQuestion().getMedia().toString() + "\n");
		Iterator<Answer> i=c.getSetOfAnswers().iterator();
		Scanner sc=new Scanner(System.in);
		while(i.hasNext()){
			System.out.println(i.next().getMedia().toString());
		}
		String s=sc.nextLine();
		if(c.isCorrectAnswer(new StringAnswer(s))){
			if(c.getReward().isPresent())
			System.out.println(c.getReward().get().getDescription() + "\n");
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
		answers.add(new StringAnswer("Boston"));
		
		Answer correctAnswer=new StringAnswer("Washington");


		
		//Genereates a position
		List<Location> locations=new ArrayList<Location>();
		locations.add(new Location(53.558, 9.927,"By the rock", Optional.<Image>absent()));
		
		ChallengeReward reward=new ChallengeReward(100, "Good job!", Optional.<Image>absent());
		
		return new Challenge("A question about America..", question, 
				answers, correctAnswer, locations, Optional.of(reward));
	}
	public static Challenge createChallenge2(){
		//Creates a question and answers
		Question question=new StringQuestion("Where was the olympic games 1996?");
		Set<Answer> answers=new HashSet<Answer>();
		answers.add(new StringAnswer("Sidney"));
		answers.add(new StringAnswer("London"));
		answers.add(new StringAnswer("Atlanta"));
		answers.add(new StringAnswer("Stockholm"));
		
		Answer correctAnswer=new StringAnswer("Atlanta");
		
		//Genereates a position
		List<Location> locations=new ArrayList<Location>();
		locations.add(new Location(41.312, 8.123,"By the rock", Optional.<Image>absent()));
		
		ChallengeReward reward=new ChallengeReward(100, "Sweet!", Optional.<Image>absent());
		
		return new Challenge("A question about OS..", question, 
				answers, correctAnswer, locations, Optional.of(reward));
	}
	
	public static Challenge createChallenge3(){
		//Creates a question and answers
		Question question=new StringQuestion("What is the highest mountain on earth?");
		Set<Answer> answers=new HashSet<Answer>();
		answers.add(new StringAnswer("Month blanc"));
		answers.add(new StringAnswer("K2"));
		answers.add(new StringAnswer("Mount Everest"));
		answers.add(new StringAnswer("Rocky Mountains"));
		
		Answer correctAnswer=new StringAnswer("Mount Everest");
		
		//Genereates a position
		List<Location> locations=new ArrayList<Location>();
		locations.add(new Location(53.551, 9.993,"By the rock", Optional.<Image>absent()));
		
		ChallengeReward reward=new ChallengeReward(100, "Awesome!", Optional.<Image>absent());
		
		return new Challenge("A question about Mountains", question, 
				answers, correctAnswer, locations, Optional.of(reward));
	}
	
	public static QuizWalkGame createGame(){
		//Fills a Map with challenges
		List<Challenge> challenges=new ArrayList<Challenge>();
		challenges.add(createChallenge1());
		challenges.add(createChallenge2());
		challenges.add(createChallenge3());
		QuizWalkGameReward reward=new QuizWalkGameReward(15,"Awesome!" , Optional.<Image>absent());
		return new QuizWalkGame("Quiz","A test-run for QuizWalk", Optional.<Image>absent(), challenges, Optional.of(reward));
	}

}
