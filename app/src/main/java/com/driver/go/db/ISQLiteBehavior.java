package com.driver.go.db;

import android.database.Cursor;

import com.driver.go.entity.QuestionItem;

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
    void saveCollectQuestion(QuestionItem q);
    void addQuestionItem2Table(String tableName,QuestionItem q);
    void deleteQuestionById(int id);
}
