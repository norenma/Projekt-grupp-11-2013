package se.chalmers.fonahano.quizwalk.activities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import se.chalmers.fonahano.quizwalk.interfaces.Answer;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.StringAnswer;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

/***
 * A Fragment to show a question as Dialog to the user When shown, it displays A
 * question and answers. When users presses one answer, it will give feedback to
 * the user, letting it know if the answer was right or wrong.
 * 
 */
public class QuestionDialogBuilder extends AlertDialog.Builder {

	class ChallengeOnClickListener implements DialogInterface.OnClickListener {

		private final int correctAnswerIndex;

		public ChallengeOnClickListener(int correctAnswerIndex) {
			super();
			this.correctAnswerIndex = correctAnswerIndex;
		}

		private AlertDialog showDialog(String s) {
			return new AlertDialog.Builder(QuestionDialogBuilder.this.getContext()).setTitle(s)
				.show();
		}

		@Override
		public void onClick(DialogInterface dialog, int which) {
			if (correctAnswerIndex == which) {
				// TODO: Change to static string resources
				showDialog("Correct!");
			} else {
				showDialog("Wrong...");
			}
		}

	}

	private int correctAnswerIndex = -1;

	// for some reason this class is "undefined without
	// implicit constructor.
	public QuestionDialogBuilder(Context arg0) {
		super(arg0);
	}

	/***
	 * Shows the question and answers of a challenge as a pop-up
	 * 
	 * @param challenge
	 *            challenge that hold a question
	 */
	public void showChallenge(Challenge challenge) {
		List<String> listOfAnswers = new ArrayList<String>();

		// Add Answers to dialog-list.
		for (Answer<String> s : challenge.getSetOfAnswers()) {
			int i = 0;

			String currentAnswer = s.getAnswer();
			listOfAnswers.add(currentAnswer);

			// Found correct answer, storing index.
			if (challenge.isCorrectAnswer(currentAnswer)) {
				correctAnswerIndex = i;
			}
			i++;
		}

		// Error handling if broken Challenge-object was retrieved.
		if (correctAnswerIndex < 0) {
			String errorMsg = "Could not find correct answer";
			Log.e(getClass().getName(),
				errorMsg);
			throw new RuntimeException(errorMsg);
		}


		// Sets up the popup
		setItems((String[]) listOfAnswers.toArray(),
			new ChallengeOnClickListener(correctAnswerIndex));
		
		// Sets the question
		setTitle(challenge.getQuestion()
			.get());
		
		// Displays the pop-up
		show();
	}
}