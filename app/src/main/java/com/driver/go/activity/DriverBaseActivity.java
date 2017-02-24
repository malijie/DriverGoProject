package com.driver.go.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.driver.go.base.Profile;
import com.driver.go.control.IntentManager;
import com.driver.go.db.SQLiteManager;
import com.driver.go.entity.QuestionItem;
import com.driver.go.utils.Logger;

/**
 * Created by Administrator on 2016/11/5.
 */
public abstract class DriverBaseActivity extends FragmentActivity {
    public SQLiteManager mSQLiteManager = null;
    public static int sOrderQuestionTotalNum = Profile.ORDER_TOTAL_ITEM;


    public abstract void initView();
    public abstract void initData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDB();

    }

    private void initDB(){
        mSQLiteManager = SQLiteManager.getInstance();
        mSQLiteManager.createTables();
    }

    public boolean isOrderTableExist(){
        return mSQLiteManager.isOrderTableHasData();
    }

    public void addOrderQuestionItem(QuestionItem q){
        mSQLiteManager.insert2OrderTable(q.getId(),q.getQuestion(),q.getAnswer(),q.getItem1(),q.getItem2(),q.getItem3(),q.getItem4(),q.getExplains(),q.getUrl());
    }
    public void addRandomQuestionItem(QuestionItem q){
        mSQLiteManager.insert2RandomTable(q.getId(),q.getQuestion(),q.getAnswer(),q.getItem1(),q.getItem2(),q.getItem3(),q.getItem4(),q.getExplains(),q.getUrl());
    }

    public void finishActivity(Activity activity){
        IntentManager.finishActivity(activity);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSQLiteManager.closeDB();
    }
}
