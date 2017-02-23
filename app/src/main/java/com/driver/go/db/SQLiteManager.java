package com.driver.go.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by malijie on 2017/2/22.
 */

public class SQLiteManager {
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

    public void insert2OrderTable(int id,String question,String answer,
                                         String item1,String item2,String item3,
                                         String item4,String explains,String url){
        String sql = "INSERT INTO " + DBConstants.ORDER_EXAM_TABLE
                + "(id,question,answer,item1,item2,item3,item4,explains,url) " +
                "VALUES ("+ id + "'" + question + "'," + "'" + answer + "'," +
                "'" + item1 + ",'"+ "'" + item2 + "',"+"'" + item3 + ",'"+
                "'" + item4 + ",'"+ "'" + explains + "',"+"'" + url + "')";
        mDB.execSQL(sql);
    }

}
