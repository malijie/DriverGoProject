package com.driver.go.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.driver.go.base.DriverGoApplication;

/**
 * Created by Administrator on 2017/2/22.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    private static SQLiteDatabase mDB;

    public SQLiteHelper() {
        super(DriverGoApplication.sContext,DBConstants.DB_NAME,null, DBConstants.DB_VERSION);
    }

    public static SQLiteDatabase getDB(){
        return mDB;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        mDB = db;
        db.beginTransaction();
        db.execSQL(SQLContainer.getCreateSubject1OrderExamTableSQL());
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
