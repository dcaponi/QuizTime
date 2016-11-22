package com.example.dominickcaponi.quiztime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private int questionNumber = 0;
    private static final String instanceStateKey = "KEY_QUESTION";
    private TextView mQuestionText;
    private TextView mChoicesText;
    private Button mAButton;
    private Button mBButton;
    private Button mCButton;
    private Button mDButton;
    private Button mBackButton;
    private Button mNextButton;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.q_1, R.string.a_1, "A"),
            new Question(R.string.q_2, R.string.a_2, "D"),
            new Question(R.string.q_3, R.string.a_3, "A"),
            new Question(R.string.q_4, R.string.a_4, "D"),
            new Question(R.string.q_5, R.string.a_5, "C"),
            new Question(R.string.q_6, R.string.a_6, "B"),
            new Question(R.string.q_7, R.string.a_7, "B"),
            new Question(R.string.q_8, R.string.a_8, "C"),
            new Question(R.string.q_9, R.string.a_9, "A"),
            new Question(R.string.q_10, R.string.a_10, "D")
    };

    private void showQuestion(){
        mQuestionText.setText(mQuestionBank[questionNumber].getQuestionID());
        mChoicesText.setText(mQuestionBank[questionNumber].getChoicesID());
    }

    private void validateResponse(String userAnswer){
        if(userAnswer == mQuestionBank[questionNumber].getCorrectAnswer()){
            Toast toast = Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
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

        mQuestionText = (TextView) findViewById(R.id.question_text);
        mChoicesText = (TextView) findViewById(R.id.choices_text);

        mAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateResponse("A");
            }
        });

        mBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateResponse("B");
            }
        });

        mCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateResponse("C");
            }
        });

        mDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateResponse("D");
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionNumber != 0){
                    questionNumber = questionNumber - 1 % mQuestionBank.length;
                    showQuestion();
                }
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionNumber != mQuestionBank.length - 1){
                    questionNumber = questionNumber + 1 % mQuestionBank.length;
                    showQuestion();
                }
            }
        });

        if(savedInstanceState != null){
            questionNumber = savedInstanceState.getInt(instanceStateKey, 0);
        }
        showQuestion();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(instanceStateKey, questionNumber);
    }
}
