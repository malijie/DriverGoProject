package com.driver.go.activity.base;

import android.os.Bundle;

import com.driver.go.db.SubjectOneSQLiteBehavior;
import com.driver.go.entity.QuestionItem;
import com.driver.go.utils.Logger;

/**
 * Created by malijie on 2017/3/17.
 */

public class SubjectOneBaseActivity extends DriverBaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    public void initView() {

    }

    public void initData() {
        mSQLiteManager.setSubjectBehavior(new SubjectOneSQLiteBehavior());
    }

    public void saveCollectQuestion(QuestionItem q){
        mSQLiteManager.saveCollectQuestion(q);
    }
}
