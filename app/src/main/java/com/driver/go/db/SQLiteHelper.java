package com.driver.go.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.driver.go.base.DriverGoApplication;

/**
 * Created by Administrator on 2017/2/22.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper() {
        super(DriverGoApplication.sContext,DBConstants.DB_NAME,null, DBConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLContainer.getCreateOrderExamTableSQL());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
