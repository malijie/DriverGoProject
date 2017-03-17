package com.driver.go.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by malijie on 2017/3/17.
 */

public class SQLiteCommon {
    private SQLiteDatabase mDB;

    public SQLiteCommon(SQLiteDatabase db){
        this.mDB = db;
    }

    public void createTables(){
        if(mDB != null){
            mDB.execSQL(SQLContainer.getCreateSubject1OrderExamTableSQL());
            mDB.execSQL(SQLContainer.getCreateSubject1RandomExamTableSQL());
            mDB.execSQL(SQLContainer.getCreateSubject1WrongQuestionTableSQL());
            mDB.execSQL(SQLContainer.getCreateSubject1CollectQuestionTableSQL());
            mDB.execSQL(SQLContainer.getCreateSubject1ExamWrongQuestionTableSQL());
            mDB.execSQL(SQLContainer.getCreateSubject1ExamRecordTableSQL());

            mDB.execSQL(SQLContainer.getCreateSubject4OrderExamTableSQL());
            mDB.execSQL(SQLContainer.getCreateSubject4RandomExamTableSQL());
            mDB.execSQL(SQLContainer.getCreateSubject4WrongQuestionTableSQL());
            mDB.execSQL(SQLContainer.getCreateSubject4CollectQuestionTableSQL());
            mDB.execSQL(SQLContainer.getCreateSubject4ExamWrongQuestionTableSQL());
            mDB.execSQL(SQLContainer.getCreateSubject4ExamRecordTableSQL());
        }
    }
}
