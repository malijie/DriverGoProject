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

    public Cursor queryRandomQuestionIdByIndex(int index) {
        cursor = mDB.rawQuery(SQLContainer.getCollectRandomQuestionIdByIndexSQL(index),null);
        cursor.moveToNext();
        return cursor;
    }

    public void clearTableData(String tableName){
        mDB.execSQL(SQLContainer.getDeleteTableSQL(tableName));
    }


    public Cursor getAllWrongQuestions(){
       return mDB.rawQuery(SQLContainer.getAllWrongQuestionsSQL(),null);
    }

    public void closeDB(){
        if(cursor != null){
            cursor.close();
        }
    }


}
