package com.driver.go.activity.base;

import android.os.Bundle;

import com.driver.go.db.SubjectOneSQLiteBehavior;
import com.driver.go.utils.Logger;

/**
 * Created by malijie on 2017/3/17.
 */

public class SubjectOneBaseActivity extends DriverBaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.d("SubjectOneBaseActivity====OnCreate");
        super.onCreate(savedInstanceState);
        initData();
    }

    public void initView() {

    }

    public void initData() {
        Logger.d("SubjectOneBaseActivity====initData,mSQLiteManager=" + mSQLiteManager);
        mSQLiteManager.setSubjectBehavior(new SubjectOneSQLiteBehavior());
    }
}
