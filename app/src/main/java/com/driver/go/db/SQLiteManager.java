package com.driver.go.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by malijie on 2017/2/22.
 */

public class SQLiteManager {
    private Cursor cursor;
    private static SQLiteManager sSQLiteManager = null;
    private SQLiteDatabase mDB;

    private SQLiteManager(){
        mDB = new SQLiteHelper().getWritableDatabase();
    }

    public static SQLiteManager getInstance(){
        if(sSQLiteManager == null){
            synchronized (SQLiteManager.class){
                if(sSQLiteManager == null){
                    sSQLiteManager = new SQLiteManager();
                }
            }
        }
        return sSQLiteManager;
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

    public boolean isOrderTableHasData(){
        cursor = mDB.rawQuery(SQLContainer.getSubject1FirstOrderExamDataSQL(),null);
        if(cursor.moveToNext()){
            return true;
        }
        return false;
    }

    public void insertQuestion2Table(String tableName,int id,String question,String answer,
                                     String item1,String item2,String item3,
                                     String item4,String explains,String url){
        String sql = "INSERT INTO " + tableName
                + "(id,question,answer,item1,item2,item3,item4,explains,url) " +
                "VALUES ("+ id + ",'" + question + "'," + "'" + answer + "'," +
                "'" + item1 + "',"+ "'" + item2 + "',"+"'" + item3 + "',"+
                "'" + item4 + "',"+ "'" + explains + "',"+"'" + url + "')";
        mDB.execSQL(sql);
    }

    public Cursor queryOrderQuestionById(int id) {
        cursor = mDB.rawQuery(SQLContainer.getSubject1OrderExamItemByIdSQL(id),null);
        cursor.moveToNext();
        return cursor;
    }

    public Cursor queryRandomQuestionById(int id) {
        cursor = mDB.rawQuery(SQLContainer.getSubject1RandomExamItemByIdSQL(id),null);
        cursor.moveToNext();
        return cursor;
    }

    public Cursor queryCollectQuestionById(int id) {
        cursor = mDB.rawQuery(SQLContainer.getSubject1CollectQuestionItemByIdSQL(id),null);
        cursor.moveToNext();
        return cursor;
    }

    public Cursor queryRandomQuestionByIndex(int index) {
        cursor = mDB.rawQuery(SQLContainer.getSubject1RandomQuestionByIndexSQL(index),null);
        cursor.moveToNext();
        return cursor;
    }

    public void clearTableData(String tableName){
        mDB.execSQL(SQLContainer.getDeleteTableSQL(tableName));
    }


    public Cursor getPractiseWrongQuestions(){
       return mDB.rawQuery(SQLContainer.getAllDataSQL(DBConstants.SUBJECT1_PRACTISE_WRONG_QUESTION_TABLE),null);
    }

    public Cursor getAllExamWrongQuestions(){
        return mDB.rawQuery(SQLContainer.getAllDataSQL(DBConstants.SUBJECT1_EXAM_WRONG_QUESTION_TABLE),null);
    }

    public boolean hasQuestions(String tableName){
        cursor = mDB.rawQuery(SQLContainer.getAllDataSQL(tableName),null);
        return cursor.moveToFirst();
    }

    public void deleteQuestionById(String tableName,int id){
        mDB.execSQL(SQLContainer.getDeleteQuestionSQL(tableName,id));
    }

    public Cursor getAllCollectQuestions() {
        return mDB.rawQuery(SQLContainer.getAllDataSQL(DBConstants.SUBJECT1_COLLECT_QUESTION_TABLE),null);
    }

    public void closeDB(){
        if(cursor != null){
            cursor.close();
        }
    }


    public void deleteItemFromCollectQuestionById(int id) {
        mDB.execSQL(SQLContainer.getDeleteQuestionSQL(DBConstants.SUBJECT1_COLLECT_QUESTION_TABLE,id));
    }

    public boolean hasCollectQuestions(String tableName) {
        cursor = mDB.rawQuery(SQLContainer.getAllDataSQL(tableName),null);
        return cursor.moveToFirst();
    }

    public int getExamWrongQuestionCount() {
        cursor = mDB.rawQuery(SQLContainer.getAllDataSQL(DBConstants.SUBJECT1_EXAM_WRONG_QUESTION_TABLE),null);
        return cursor.getCount();
    }

    public void insertExamRecordData(String tableName,String date,int score){
        mDB.execSQL(SQLContainer.getInsertExamRecordDataSQL(tableName,date,score));
    }

    public Cursor getExamRecord(){
        cursor = mDB.rawQuery(SQLContainer.getAllDataSQL(DBConstants.SUBJECT1_C1_EXAM_RECORD_TABLE),null);
        return cursor;
    }

    public int getMaxScore(){
        cursor = mDB.rawQuery(SQLContainer.getSubject1MaxScoreSQL(),null);
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(0)));
    }
}
