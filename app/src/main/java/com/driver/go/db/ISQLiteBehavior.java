package com.driver.go.db;

import android.database.Cursor;

/**
 * Created by malijie on 2017/3/17.
 */

public interface ISQLiteBehavior {
    Cursor queryOrderQuestionById(int id);

}
