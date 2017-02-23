package com.driver.go.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.driver.go.utils.Logger;

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
        }
    }

    public boolean isOrderTableHasData(){
        cursor = mDB.rawQuery(SQLContainer.getFirstOrderExamDataSQL(),null);
        if(cursor.moveToFirst()){
            return true;
        }
        return false;
    }

    public void insert2OrderTable(int id,String question,String answer,
                                         String item1,String item2,String item3,
                                         String item4,String explains,String url){
        String sql = "INSERT INTO " + DBConstants.ORDER_EXAM_TABLE
                + "(id,question,answer,item1,item2,item3,item4,explains,url) " +
                "VALUES ("+ id + ",'" + question + "'," + "'" + answer + "'," +
                "'" + item1 + "',"+ "'" + item2 + "',"+"'" + item3 + "',"+
                "'" + item4 + "',"+ "'" + explains + "',"+"'" + url + "')";
        mDB.execSQL(sql);
    }

    public void insert2RandomTable(int id,String question,String answer,
                                  String item1,String item2,String item3,
                                  String item4,String explains,String url){
        String sql = "INSERT INTO " + DBConstants.RANDOM_EXAM_TABLE
                + "(id,question,answer,item1,item2,item3,item4,explains,url) " +
                "VALUES ("+ id + ",'" + question + "'," + "'" + answer + "'," +
                "'" + item1 + "',"+ "'" + item2 + "',"+"'" + item3 + "',"+
                "'" + item4 + "',"+ "'" + explains + "',"+"'" + url + "')";
        mDB.execSQL(sql);
    }

    public Cursor queryOrderQuestionById(int id){
        cursor = mDB.rawQuery(SQLContainer.getOrderExamItemByIdSQL(id),null);
        return cursor;
    }

    public void closeDB(){
        if(cursor != null){
          cursor.close();
        }
    }

}
