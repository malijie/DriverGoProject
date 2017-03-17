package com.driver.go.db;

import android.database.Cursor;

/**
 * Created by malijie on 2017/3/17.
 */

public interface ISQLiteBehavior {
    Cursor queryOrderQuestionById(int id);
    Cursor queryCollectQuestionById(int id);
    Cursor queryRandomQuestionByIndex(int index);
    Cursor getAllCollectQuestions();
    Cursor getPractiseWrongQuestions();
    Cursor getAllExamWrongQuestions();
    void deleteItemFromCollectQuestionById(int id);
    int getExamWrongQuestionCount();
    int getMaxScore();
    Cursor getExamRecordResult();
    boolean checkCollected(int id);
}
