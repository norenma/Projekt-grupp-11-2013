package se.chalmers.fonahano.quizwalk.presentation;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.interfaces.Answer;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame.ChallengeState;
import android.app.AlertDialog;
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
	private class ChallengeOnClickListener implements
			DialogInterface.OnClickListener {

		private final int correctAnswerIndex;

		public ChallengeOnClickListener(int correctAnswerIndex) {
			this.correctAnswerIndex = correctAnswerIndex;
		}

		private AlertDialog showDialog(String s) {
			return new AlertDialog.Builder(
					QuestionDialogBuilder.this.getContext()).setTitle(s).show();
		}

		@Override
		public void onClick(DialogInterface dialog, int which) {
			if (correctAnswerIndex == which) {
				StateSingleton.INSTANCE.getActiveQuizWalk().get()
						.setChallengeState(challenge, ChallengeState.COMPLETED);
				showDialog(QuestionDialogBuilder.this.getContext()
						.getResources()
						.getString(R.string.correct_answer_prompt));
			} else {
				StateSingleton.INSTANCE.getActiveQuizWalk().get()
						.setChallengeState(challenge, ChallengeState.FAILED);
				showDialog(QuestionDialogBuilder.this.getContext()
						.getResources()
						.getString(R.string.incorrect_answer_prompt));
			}
		}

	}

	private Challenge challenge;
	private int correctAnswerIndex = -1;

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
		this.challenge = challenge;
		List<String> listOfAnswers = new ArrayList<String>();

		int i = 0;
		// Add Answers to dialog-list.
		for (Answer<String> s : challenge.getSetOfAnswers()) {

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
			Log.e(getClass().getName(), QuestionDialogBuilder.this.getContext()
					.getResources().getString(R.string.could_not_find_answer));
			throw new RuntimeException(QuestionDialogBuilder.this.getContext()
					.getResources().getString(R.string.could_not_find_answer));
		}
		String[] itemsArray = listOfAnswers.toArray(new String[listOfAnswers
				.size()]);
		Log.d("derp", listOfAnswers.size() + " " + itemsArray.length);

		// Sets up the popup
		setItems(itemsArray, new ChallengeOnClickListener(correctAnswerIndex));

		// Sets the question
		setTitle(challenge.getQuestion().get());

		// Displays the pop-up
		show();
	}

}