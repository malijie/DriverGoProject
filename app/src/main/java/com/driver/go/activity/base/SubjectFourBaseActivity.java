package com.driver.go.activity.base;

import android.os.Bundle;

import com.driver.go.db.SubjectFourSQLiteBehavior;
import com.driver.go.db.SubjectOneSQLiteBehavior;
import com.driver.go.entity.QuestionItem;
import com.driver.go.utils.Logger;

/**
 * Created by malijie on 2017/3/17.
 */

public class SubjectFourBaseActivity extends DriverBaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

    }

    private void initView() {

    }

    private void initData() {
        mSQLiteManager.setSubjectBehavior(SubjectFourSQLiteBehavior.getInstance());
    }

    public void saveCollectQuestion(QuestionItem q){
        mSQLiteManager.saveCollectQuestion(q);
    }
}
