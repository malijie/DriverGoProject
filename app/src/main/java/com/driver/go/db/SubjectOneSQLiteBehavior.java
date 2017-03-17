package com.driver.go.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by malijie on 2017/3/17.
 */

public class SubjectOneSQLiteBehavior implements ISQLiteBehavior{
    private SQLiteDatabase mDB;
    private Cursor cursor = null;

    public SubjectOneSQLiteBehavior(){
        mDB = new SQLiteHelper().getWritableDatabase();
    }
    @Override
    public Cursor queryOrderQuestionById(int id) {
        cursor = mDB.rawQuery(SQLContainer.getSubject1OrderExamItemByIdSQL(id),null);
        cursor.moveToNext();
        return cursor;
    }

    public Cursor queryCollectQuestionById(int id) {
        cursor = mDB.rawQuery(SQLContainer.getSubject1CollectQuestionItemByIdSQL(id),null);
        cursor.moveToNext();
        return cursor;
    }

    @Override
    public Cursor queryRandomQuestionByIndex(int index) {
        cursor = mDB.rawQuery(SQLContainer.getSubject1RandomQuestionByIndexSQL(index),null);
        cursor.moveToNext();
        return cursor;
    }

    @Override
    public Cursor getAllCollectQuestions() {
        return mDB.rawQuery(SQLContainer.getAllDataSQL(DBConstants.SUBJECT1_COLLECT_QUESTION_TABLE),null);
    }

    @Override
    public Cursor getPractiseWrongQuestions() {
        return mDB.rawQuery(SQLContainer.getAllDataSQL(DBConstants.SUBJECT1_PRACTISE_WRONG_QUESTION_TABLE),null);
    }

    @Override
    public Cursor getAllExamWrongQuestions() {
        return  mDB.rawQuery(SQLContainer.getAllDataSQL(DBConstants.SUBJECT1_EXAM_WRONG_QUESTION_TABLE),null);
    }

    @Override
    public void deleteItemFromCollectQuestionById(int id) {
        mDB.execSQL(SQLContainer.getDeleteQuestionSQL(DBConstants.SUBJECT1_COLLECT_QUESTION_TABLE,id));
    }

    @Override
    public int getExamWrongQuestionCount() {
        cursor = mDB.rawQuery(SQLContainer.getAllDataSQL(DBConstants.SUBJECT1_EXAM_WRONG_QUESTION_TABLE),null);
        return cursor.getCount();
    }

    @Override
    public int getMaxScore() {
        cursor = mDB.rawQuery(SQLContainer.getSubject1MaxScoreSQL(),null);
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(0)));
    }

    @Override
    public Cursor getExamRecordResult() {
        cursor = mDB.rawQuery(SQLContainer.getAllDataSQL(DBConstants.SUBJECT1_C1_EXAM_RECORD_TABLE),null);
        return cursor;
    }
}
