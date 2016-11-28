package com.example.dominickcaponi.quiztime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String instanceStateCheat = "KEY_CHEATED";
    private static final String EXTRA_ANSWER = "com.example.dominickcaponi.quiztime.answer";
    private static final String EXTRA_ANSWER_SHOWN = "com.example.dominickcaponi.quiztime.answer_shown";
    private char mCurrentAnswer;
    private boolean mCheated;
    private TextView mAnswerTextView;
    private Button mShowAnswer;

    public static Intent newCheatIntent(Context packageContext, char currentAnswer){
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER, currentAnswer);
        return i;
    }

    public static boolean answerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mCheated = false;
        if(savedInstanceState != null){
            mCheated = savedInstanceState.getBoolean(instanceStateCheat, false);
        }

        mCurrentAnswer = this.getIntent().getCharExtra(EXTRA_ANSWER, 'E');
        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mShowAnswer = (Button) findViewById(R.id.show_answer_button);

        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnswerTextView.setText("The Correct Answer is " + mCurrentAnswer);
                mCheated = true;
                setAnswerShownResult(mCheated);
            }
        });
        setAnswerShownResult(mCheated);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(instanceStateCheat, mCheated);
    }
}
