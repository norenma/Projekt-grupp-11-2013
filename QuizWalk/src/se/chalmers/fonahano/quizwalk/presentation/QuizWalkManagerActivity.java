package se.chalmers.fonahano.quizwalk.presentation;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.database.GameDatabaseManager;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.GsonBuilder;

public class QuizWalkManagerActivity extends Activity {

	ListView listView;

	List<QuizWalkGame> listOfQuizWalks;

	ArrayAdapter<QuizWalkGame> listViewOfQuizWalks;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// remove actionbar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);

		GameDatabaseManager.init(this);

		ViewGroup contentView = (ViewGroup) getLayoutInflater().inflate(
				R.layout.activity_quiz_walk_manager, null);
		listView = (ListView) contentView.findViewById(R.id.list_view);

		setContentView(contentView);
	}

	@Override
	protected void onStart() {
		super.onStart();
		setupListView(listView);
	}

	private void setupListView(ListView listView) {

		final List<QuizWalkGame> allQuizWalkGames = GameDatabaseManager
				.getInstance().getAllQuizWalkGame();

		List<String> titles = new ArrayList<String>();
		for (QuizWalkGame q : allQuizWalkGames) {
			titles.add("ID: " + q.getId() + " - Name: " + q.getName());
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, titles);
		listView.setAdapter(adapter);

		final Activity activity = this;

		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				QuizWalkGame clickedQuizWalk = allQuizWalkGames.get(position);
				Intent intent = new Intent(activity, DebugActivity.class);
				intent.putExtra(C.Data.JSON_DATA, new GsonBuilder()
						.setPrettyPrinting().create().toJson(clickedQuizWalk));
				startActivity(intent);
			}
		});
	}

	public void generateQuizWalk(View view) {
		QuizWalkGame g = null;
		GameDatabaseManager.getInstance().addQuizWalkGame(g);
		listOfQuizWalks.add(g);
	}

}
