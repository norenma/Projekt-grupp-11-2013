package se.chalmers.fonahano.quizwalk.activities;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.database.GameDatabaseManager;
import se.chalmers.fonahano.quizwalk.database.GameOrmLiteSQLiteOpenHelper;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import temp.debug.tortal.DebugFactory;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

public class QuizWalkManagerActivity extends Activity {

	ListView listView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//TODO: Uncomment to delete database
		//deleteDatabase(GameOrmLiteSQLiteOpenHelper.DATABASE_NAME);
		
		GameDatabaseManager.init(this);

		ViewGroup contentView = (ViewGroup) getLayoutInflater().inflate(R.layout.activity_quiz_walk_manager,
			null);
		listView = (ListView) contentView.findViewById(R.id.list_view);

		Button btn = (Button) contentView.findViewById(R.id.button_add);
		// TODO: Make it work :)
		// setupButton(btn);
		setContentView(contentView);
	}

	@Override
	protected void onStart() {
		super.onStart();
		setupListView(listView);
	}

	private void setupListView(ListView listView) {

		// TODO: Debugging
		addEntryFromDebugFactory();

		final List<QuizWalkGame> allQuizWalkGames = GameDatabaseManager.getInstance()
			.getAllQuizWalkGame();

		List<String> titles = new ArrayList<String>();
		for (QuizWalkGame q : allQuizWalkGames) {
			titles.add("ID: " + q.getId() + " - Name: " + q.getName());
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_list_item_1,
			titles);
		listView.setAdapter(adapter);

		final Activity activity = this;

		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				QuizWalkGame clickedQuizWalk = allQuizWalkGames.get(position);
				Intent intent = new Intent(activity,
					DebugActivity.class);
				intent.putExtra("json_data",
					new Gson().toJson(clickedQuizWalk));
				startActivity(intent);
			}
		});
	}

	// private void setupButton(Button btn) {
	// final Activity activity = this;
	// btn.setOnClickListener(new OnClickListener() {
	// public void onClick(View v) {
	// Intent intent = new Intent(activity, AddWishListActivity.class);
	// startActivity(intent);
	// }
	// });
	// }

	// TODO: Persisting static QuizWalkGame
	// (temp.debub.tortal.DebugFactory.java)
	private void addEntryFromDebugFactory() {
		QuizWalkGame g = DebugFactory.getRandomTortalChalmersQuizWalkGame1();
		GameDatabaseManager.getInstance()
			.addQuizWalkGame(g,
				true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz_walk_manager,
			menu);
		return true;
	}

}
