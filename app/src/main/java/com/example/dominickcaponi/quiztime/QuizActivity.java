package com.example.dominickcaponi.quiztime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private int questionNumber = 0;
    private static final String instanceStateKey = "KEY_QUESTION";
    private static final String instanceStateCheat = "KEY_CHEATED";
    private static final int REQ_CODE_CHEAT = 0;
    private boolean mCheated;
    private TextView mQuestionText;
    private TextView mChoicesText;
    private Button mAButton;
    private Button mBButton;
    private Button mCButton;
    private Button mDButton;
    private Button mBackButton;
    private Button mNextButton;
    private Button mCheatButton;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.q_1, R.string.a_1, 'A'),
            new Question(R.string.q_2, R.string.a_2, 'D'),
            new Question(R.string.q_3, R.string.a_3, 'A'),
            new Question(R.string.q_4, R.string.a_4, 'D'),
            new Question(R.string.q_5, R.string.a_5, 'C'),
            new Question(R.string.q_6, R.string.a_6, 'B'),
            new Question(R.string.q_7, R.string.a_7, 'B'),
            new Question(R.string.q_8, R.string.a_8, 'C'),
            new Question(R.string.q_9, R.string.a_9, 'A'),
            new Question(R.string.q_10, R.string.a_10, 'D')
    };

    private void showQuestion(){
        mQuestionText.setText(mQuestionBank[questionNumber].getQuestionID());
        mChoicesText.setText(mQuestionBank[questionNumber].getChoicesID());
    }

    private void validateResponse(char userAnswer){
        if(userAnswer == mQuestionBank[questionNumber].getCorrectAnswer() && mCheated){
            Toast toast = Toast.makeText(this, "You Cheated. -1 point :(", Toast.LENGTH_SHORT);
            toast.show();
        }
        if(userAnswer == mQuestionBank[questionNumber].getCorrectAnswer() && !mCheated){
            Toast toast = Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT);
            toast.show();
        }
        if(userAnswer != mQuestionBank[questionNumber].getCorrectAnswer() && mCheated){
            Toast toast = Toast.makeText(this, "Cheated and still wrong :P", Toast.LENGTH_SHORT);
            toast.show();
        }
        if(userAnswer != mQuestionBank[questionNumber].getCorrectAnswer() && !mCheated){
            Toast toast = Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mAButton = (Button) findViewById(R.id.option_a);
        mBButton = (Button) findViewById(R.id.option_b);
        mCButton = (Button) findViewById(R.id.option_c);
        mDButton = (Button) findViewById(R.id.option_d);
        mBackButton = (Button) findViewById(R.id.back_button);
        mNextButton = (Button) findViewById(R.id.next_button);
        mCheatButton = (Button) findViewById(R.id.cheat_button);

        mQuestionText = (TextView) findViewById(R.id.question_text);
        mChoicesText = (TextView) findViewById(R.id.choices_text);

        if(savedInstanceState != null){
            questionNumber = savedInstanceState.getInt(instanceStateKey, 0);
            mCheated = savedInstanceState.getBoolean(instanceStateCheat, false);
        }

        showQuestion();

        mAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateResponse('A');
            }
        });

        mBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateResponse('B');
            }
        });

        mCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateResponse('C');
            }
        });

        mDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateResponse('D');
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionNumber != 0){
                    questionNumber = questionNumber - 1 % mQuestionBank.length;
                    mCheated = false;
                    showQuestion();
                }
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionNumber != mQuestionBank.length - 1){
                    questionNumber = questionNumber + 1 % mQuestionBank.length;
                    mCheated = false;
                    showQuestion();
                }
            }
        });

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                char currentAnswer = mQuestionBank[questionNumber].getCorrectAnswer();
                Intent i = CheatActivity.newCheatIntent(QuizActivity.this, currentAnswer);
                startActivityForResult(i, REQ_CODE_CHEAT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent responseIntentData){
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQ_CODE_CHEAT){
            if(responseIntentData == null){
                return;
            }
            mCheated = CheatActivity.answerShown(responseIntentData);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(instanceStateKey, questionNumber);
        savedInstanceState.putBoolean(instanceStateCheat, mCheated);
    }
}
