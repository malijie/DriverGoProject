package com.driver.go.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by malijie on 2017/3/17.
 */

public class SubjectFourSQLiteBehavior implements ISQLiteBehavior{
    private SQLiteDatabase mDB;
    private Cursor cursor = null;

    public SubjectFourSQLiteBehavior(){
        mDB = new SQLiteHelper().getWritableDatabase();
    }
    @Override
    public Cursor queryOrderQuestionById(int id) {
        cursor = mDB.rawQuery(SQLContainer.getSubject4OrderExamItemByIdSQL(id),null);
        cursor.moveToNext();
        return cursor;
    }
}
