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
            mDB.execSQL(SQLContainer.getCreateOrderExamTableSQL());
            mDB.execSQL(SQLContainer.getCreateRandomExamTableSQL());
            mDB.execSQL(SQLContainer.getCreateWrongQuestionTableSQL());
            mDB.execSQL(SQLContainer.getCreateCollectQuestionTableSQL());
            mDB.execSQL(SQLContainer.getCreateExamWrongQuestionTableSQL());
            mDB.execSQL(SQLContainer.getCreateC1ExamRecordTableSQL());
        }
    }

    public boolean isOrderTableHasData(){
        cursor = mDB.rawQuery(SQLContainer.getFirstOrderExamDataSQL(),null);
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
        cursor = mDB.rawQuery(SQLContainer.getOrderExamItemByIdSQL(id),null);
        cursor.moveToNext();
        return cursor;
    }

    public Cursor queryRandomQuestionById(int id) {
        cursor = mDB.rawQuery(SQLContainer.getRandomExamItemByIdSQL(id),null);
        cursor.moveToNext();
        return cursor;
    }

    public Cursor queryCollectQuestionById(int id) {
        cursor = mDB.rawQuery(SQLContainer.getCollectQuestionItemByIdSQL(id),null);
        cursor.moveToNext();
        return cursor;
    }

    public Cursor queryRandomQuestionByIndex(int index) {
        cursor = mDB.rawQuery(SQLContainer.getRandomQuestionByIndexSQL(index),null);
        cursor.moveToNext();
        return cursor;
    }

    public void clearTableData(String tableName){
        mDB.execSQL(SQLContainer.getDeleteTableSQL(tableName));
    }


    public Cursor getPractiseWrongQuestions(){
       return mDB.rawQuery(SQLContainer.getAllDataSQL(DBConstants.PRACTISE_WRONG_QUESTION_TABLE),null);
    }

    public Cursor getAllExamWrongQuestions(){
        return mDB.rawQuery(SQLContainer.getAllDataSQL(DBConstants.EXAM_WRONG_QUESTION_TABLE),null);
    }

    public boolean hasQuestions(String tableName){
        cursor = mDB.rawQuery(SQLContainer.getAllDataSQL(tableName),null);
        return cursor.moveToFirst();
    }

    public void deleteQuestionById(String tableName,int id){
        mDB.execSQL(SQLContainer.getDeleteQuestionSQL(tableName,id));
    }

    public Cursor getAllCollectQuestions() {
        return mDB.rawQuery(SQLContainer.getAllDataSQL(DBConstants.COLLECT_QUESTION_TABLE),null);
    }

    public void closeDB(){
        if(cursor != null){
            cursor.close();
        }
    }


    public void deleteItemFromCollectQuestionById(int id) {
        mDB.execSQL(SQLContainer.getDeleteQuestionSQL(DBConstants.COLLECT_QUESTION_TABLE,id));
    }

    public boolean hasCollectQuestions() {
        cursor = mDB.rawQuery(SQLContainer.getAllDataSQL(DBConstants.COLLECT_QUESTION_TABLE),null);
        return cursor.moveToFirst();
    }

    public int getExamWrongQuestionCount() {
        cursor = mDB.rawQuery(SQLContainer.getAllDataSQL(DBConstants.EXAM_WRONG_QUESTION_TABLE),null);
        return cursor.getCount();
    }

    public void insertExamRecordData(String tableName,String date,int score){
        mDB.execSQL(SQLContainer.getInsertExamRecordDataSQL(tableName,date,score));
    }

    public Cursor getExamRecord(){
        cursor = mDB.rawQuery(SQLContainer.getAllDataSQL(DBConstants.C1_EXAM_RECORD_TABLE),null);
        return cursor;
    }
}
