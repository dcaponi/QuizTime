package com.example.dominickcaponi.quiztime;

/**
 * Created by dominickcaponi on 11/20/16.
 */

public class Question {
    private int mQuestionID;
    private int mChoicesID;
    private char mCorrectAnswer;
    private boolean mAnswered;

    public Question(int questionID, int choicesID, char correctAnswer) {
        this.mQuestionID = questionID;
        this.mChoicesID = choicesID;
        this.mCorrectAnswer = correctAnswer;
        this.mAnswered = false;
    }

    public int getQuestionID() {
        return mQuestionID;
    }

    public int getChoicesID() {
        return mChoicesID;
    }

    public char getCorrectAnswer() {
        return mCorrectAnswer;
    }

    public boolean isAnswered() { return mAnswered; }

    public boolean answerQuestion(){
        this.mAnswered = true;
        return this.mAnswered;
    }
}
