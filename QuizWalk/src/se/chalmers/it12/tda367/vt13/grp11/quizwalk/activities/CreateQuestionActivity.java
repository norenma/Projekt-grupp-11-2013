package se.chalmers.it12.tda367.vt13.grp11.quizwalk.activities;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.R;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.R.id;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.R.layout;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;



public class CreateQuestionActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_question);
	}

}
