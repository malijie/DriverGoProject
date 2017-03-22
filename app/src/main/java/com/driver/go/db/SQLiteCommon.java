package com.driver.go.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by malijie on 2017/3/17.
 */

public class SQLiteCommon {
    private SQLiteDatabase mDB;
    private Cursor cursor;

    public SQLiteCommon(SQLiteDatabase db){
        this.mDB = db;
    }

    public void createTables(){
        if(mDB != null && !mDB.isDbLockedByCurrentThread()){
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

    public boolean isOrderTableHasData(){
        cursor = mDB.rawQuery(SQLContainer.getSubject1FirstOrderExamDataSQL(),null);
        if(cursor.moveToNext()){
            return true;
        }
        return false;
    }

    /**
     * 清空表数据
     * @param tableName
     */
    public void clearTableData(String tableName){
        mDB.execSQL(SQLContainer.getDeleteTableSQL(tableName));
    }


    public boolean hasQuestions(String tableName){
        cursor = mDB.rawQuery(SQLContainer.getAllDataSQL(tableName),null);
        return cursor.moveToFirst();
    }


    public boolean hasCollectQuestions(String tableName) {
        cursor = mDB.rawQuery(SQLContainer.getAllDataSQL(tableName),null);
        return cursor.moveToFirst();
    }

    public void insertExamRecordData(String tableName,String date,int score){
        mDB.execSQL(SQLContainer.getInsertExamRecordDataSQL(tableName,date,score));
    }

    public void closeDB(){
        if(cursor != null){
            cursor.close();
        }
    }
}
