package se.chalmers.it12.tda367.vt13.grp11.quizwalk.activities;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.fonahano.quizwalk.database.GameDatabaseManager;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.R;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.QuizWalkGame;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class QuizWalkManagerActivity extends Activity {

	ListView listView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameDatabaseManager.init(this);

		ViewGroup contentView = (ViewGroup) getLayoutInflater().inflate(
				R.layout.activity_quiz_walk_manager, null);
		listView = (ListView) contentView.findViewById(R.id.list_view);

		Button btn = (Button) contentView.findViewById(R.id.button_add);
		//TODO: Make it work :)
		//setupButton(btn);
		setContentView(contentView);
	}

	@Override
	protected void onStart() {
		super.onStart();
		setupListView(listView);
	}

	private void setupListView(ListView lv) {
		final List<QuizWalkGame> allQuizWalkGames = GameDatabaseManager.getInstance()
				.getAllQuizWalkGame();

		List<String> titles = new ArrayList<String>();
		for (QuizWalkGame q : allQuizWalkGames) {
			titles.add(q.getName());
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, titles);
		lv.setAdapter(adapter);

		final Activity activity = this;
		
//		lv.setOnItemClickListener(new OnItemClickListener() {

			//TODO: Next Screen.
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				QuizWalkGame clickedQuizWalk = allQuizWalkGames.get(position);
//				Intent intent = new Intent(activity, QuizWalkEditorActivity.class);
//				intent.putExtra(Constants.keyQuizWalkGametId,
//						clickedQuizWalk.getId());
//				//startActivity(intent);
//			}
//		});
	}

//	private void setupButton(Button btn) {
//		final Activity activity = this;
//		btn.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				Intent intent = new Intent(activity, AddWishListActivity.class);
//				startActivity(intent);
//			}
//		});
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz_walk_manager, menu);
		return true;
	}

}
