package se.chalmers.fonahano.quizwalk.activities;

import java.util.Iterator;

import se.chalmers.fonahano.quizwalk.model.Answer;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.StringAnswer;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/***
 * A Fragment-class to show a question as a pop-up
 * 
 * @author Markus Andersson Norén
 * 
 */
@SuppressLint("ValidFragment")
public class QuestionDialogBuilder extends AlertDialog.Builder {

	public QuestionDialogBuilder(Context arg0) {
		super(arg0);
	}

	private String[] answers = new String[4];
	private int rightNbr;

	/***
	 * Shows the question and answers of a challenge as a pop-up
	 * 
	 * @param c
	 *            challenge that hold a question
	 */
	public void showChallenge(Challenge c) {
		// Gets answers from challenge
		Iterator<Answer> answersIt = c.getSetOfAnswers().iterator();
		for (int i = 0; i < 4; i++) {
			if (answersIt.hasNext())
				answers[i] = (String) answersIt.next().getMedia();
			else
				break;
		}

		for (int i = 0; i < answers.length; i++) {
			if (c.isCorrectAnswer(new StringAnswer(answers[i])))
				rightNbr = i;
		}

		// Sets up the popup
		this.setItems(answers, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface d, int choice) {
				if (choice == rightNbr) {
					AlertDialog.Builder ab = new AlertDialog.Builder(
							getContext());
					ab.setTitle("Rätt");
					ab.show();

				} else {
					AlertDialog.Builder ab = new AlertDialog.Builder(
							getContext());
					ab.setTitle("Fel");
					ab.show();

				}

			}
		});
		// Sets the question
		this.setTitle((String) c.getQuestion().getMedia());
		// Displays the pop-up
		this.show();
	}

}