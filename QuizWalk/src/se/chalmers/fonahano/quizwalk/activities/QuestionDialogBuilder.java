package se.chalmers.fonahano.quizwalk.activities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import se.chalmers.fonahano.quizwalk.interfaces.Answer;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.StringAnswer;
import se.chalmers.fonahano.quizwalk.model.Utilities;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/***
 * A Fragment to show a question as Dialog to the user
 * When shown, it displays A question and answers. When users 
 * presses one answer, it will give feedback to the user, letting 
 * it know if the answer was right or wrong. 
 * 
 * @author Markus Andersson Nor�n
 * 
 */
@SuppressLint("ValidFragment")
public class QuestionDialogBuilder extends AlertDialog.Builder {

	//for some reason this class is "undefined without 
	//implicit constructor. 
	public QuestionDialogBuilder(Context arg0) {
		super(arg0);
	}


	/***
	 * Shows the question and answers of a challenge as a pop-up
	 * 
	 * @param c
	 *            challenge that hold a question
	 */
	public void showChallenge(Challenge c) {
		// Gets answers from challenge
		//Iterator<Answer<String>> answersIt = c.getSetOfAnswers().iterator();
		List<String> answersTmp=new ArrayList<String>();
		int rightNbrTmp=1;
//		for (int i = 0; i < 4; i++) {
//			if (answersIt.hasNext())
//				answersTmp.set(i, (String) answersIt.next().getAnswer());
//			else
//				break;
//		}
		
		for(Answer<String> s: Utilities.checkNotNullOrEmpty(c.getSetOfAnswers(), "Some anwsers are null or not present")){
			answersTmp.add(s.getAnswer());
		}

		for (int i = 0; i < answersTmp.size(); i++) {
			if (c.isCorrectAnswer(new StringAnswer(answersTmp.get(i))))
				rightNbrTmp = i;
		}
		final int rightNbr=rightNbrTmp;

		// Sets up the popup
		this.setItems((String[])(answersTmp.toArray()), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface d, int choice) {
				if (choice == rightNbr) {
					AlertDialog.Builder ab = new AlertDialog.Builder(
							getContext());
					ab.setTitle("R�tt");
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
		this.setTitle(c.getQuestion().get());
		// Displays the pop-up
		this.show();
	}

}