package com.ytvc.training.mythquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button mTrueButton;
	private Button mFalseButton;
	private TextView mQuestionTextView;
	private Button mNextButton;
	private TrueFalse[] mQuestionBank = new TrueFalse[] {
			new TrueFalse(R.string.question_rain, false),
			new TrueFalse(R.string.question_alcohol, false),
			new TrueFalse(R.string.question_cat, true),
			new TrueFalse(R.string.question_gas, false),
			new TrueFalse(R.string.question_train, false) };

	private int mCurrentIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mQuestionTextView = (TextView) findViewById(R.id.question_textview);
		updateQuestion();
		mTrueButton = (Button) findViewById(R.id.true_button);
		mTrueButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkAnswer(true);
			}
		});

		mFalseButton = (Button) findViewById(R.id.false_button);
		mFalseButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkAnswer(false);
			}
		});
		
		mNextButton = (Button) findViewById(R.id.next_button);
		mNextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				updateQuestion();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void updateQuestion() {
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
	}
	
	private void checkAnswer(boolean userPressedTrue) {
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
		int msgId = 0;
		if (answerIsTrue == userPressedTrue) {
			msgId = R.string.correct_toast;
		} else {
			msgId = R.string.incorrect_toast;
		}
		
		Toast.makeText(this, msgId, Toast.LENGTH_SHORT).show();
	}
}
