package com.driver.go.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.driver.go.entity.QuestionItem;
import com.driver.go.utils.Logger;
import com.driver.go.utils.ToastManager;


/**
 * Created by malijie on 2017/3/17.
 */

public class SubjectFourSQLiteBehavior implements ISQLiteBehavior{
    private static SubjectFourSQLiteBehavior sSubjectFourSQLiteBehavior = null;
    private SQLiteDatabase mDB;
    private Cursor cursor = null;

    private SubjectFourSQLiteBehavior(){
        mDB = SQLiteHelper.getDB();
    }

    public static SubjectFourSQLiteBehavior getInstance(){
        if(sSubjectFourSQLiteBehavior == null){
            synchronized (SubjectOneSQLiteBehavior.class){
                if(sSubjectFourSQLiteBehavior == null){
                    sSubjectFourSQLiteBehavior = new SubjectFourSQLiteBehavior();
                }
            }
        }
        return sSubjectFourSQLiteBehavior;
    }

    @Override
    public Cursor queryOrderQuestionById(int id) {
        cursor = mDB.rawQuery(SQLContainer.getSubject4OrderExamItemByIdSQL(id),null);
        cursor.moveToNext();
        return cursor;
    }

    @Override
    public Cursor queryCollectQuestionById(int id) {
        cursor = mDB.rawQuery(SQLContainer.getSubject4CollectQuestionItemByIdSQL(id),null);
        cursor.moveToNext();
        return cursor;
    }

    @Override
    public Cursor queryRandomQuestionByIndex(int index) {
        cursor = mDB.rawQuery(SQLContainer.getSubject4RandomQuestionByIndexSQL(index),null);
        cursor.moveToNext();
        return cursor;
    }

    @Override
    public Cursor getAllCollectQuestions() {
        return mDB.rawQuery(SQLContainer.getAllDataSQL(DBConstants.SUBJECT4_COLLECT_QUESTION_TABLE),null);
    }

    @Override
    public Cursor getPractiseWrongQuestions() {
        return mDB.rawQuery(SQLContainer.getAllDataSQL(DBConstants.SUBJECT4_PRACTISE_WRONG_QUESTION_TABLE),null);
    }

    @Override
    public Cursor getAllExamWrongQuestions() {
        return mDB.rawQuery(SQLContainer.getAllDataSQL(DBConstants.SUBJECT4_EXAM_WRONG_QUESTION_TABLE),null);
    }

    @Override
    public void deleteItemFromCollectQuestionById(int id) {
        mDB.execSQL(SQLContainer.getDeleteQuestionSQL(DBConstants.SUBJECT4_COLLECT_QUESTION_TABLE,id));
    }

    @Override
    public int getExamWrongQuestionCount() {
        cursor = mDB.rawQuery(SQLContainer.getAllDataSQL(DBConstants.SUBJECT4_EXAM_WRONG_QUESTION_TABLE),null);
        return cursor.getCount();
    }

    @Override
    public int getMaxScore() {
        cursor = mDB.rawQuery(SQLContainer.getSubject4MaxScoreSQL(),null);
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(0)));
    }

    @Override
    public Cursor getExamRecordResult() {
        cursor = mDB.rawQuery(SQLContainer.getAllDataSQL(DBConstants.SUBJECT4_C1_EXAM_RECORD_TABLE),null);
        return cursor;
    }

    @Override
    public boolean checkCollected(int id) {
        return queryCollectQuestionById(id).moveToFirst();
    }

    @Override
    public void saveCollectQuestion(QuestionItem q) {
        String sql = "INSERT INTO " + DBConstants.SUBJECT4_COLLECT_QUESTION_TABLE
                + "(id,question,answer,item1,item2,item3,item4,explains,url) " +
                "VALUES ("+ q.getId()+ ",'" + q.getQuestion() + "'," + "'" + q.getAnswer() + "'," +
                "'" + q.getItem1() + "',"+ "'" + q.getItem2() + "',"+"'" + q.getItem4() + "',"+
                "'" + q.getItem4() + "',"+ "'" + q.getExplains() + "',"+"'" + q.getUrl() + "')";
        mDB.execSQL(sql);
    }


    @Override
    public void deleteQuestionById(int id) {
        mDB.execSQL(SQLContainer.getDeleteQuestionSQL(DBConstants.SUBJECT4_PRACTISE_WRONG_QUESTION_TABLE,id));
    }

}
