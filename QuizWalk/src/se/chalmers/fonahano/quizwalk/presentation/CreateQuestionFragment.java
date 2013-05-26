package se.chalmers.fonahano.quizwalk.presentation;

import se.chalmers.fonahano.quizwalk.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/***
 * A class to collect data from user to create a StringQuestion. Class is called
 * from CreateGameActivite and it«s intent should already contain a
 * LatLng-location
 * 
 */
public class CreateQuestionFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_create_question, container,
				false);
	}
}
