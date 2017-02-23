package com.driver.go.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.driver.go.R;
import com.driver.go.db.SQLContainer;
import com.driver.go.db.SQLiteHelper;
import com.driver.go.db.SQLiteManager;
import com.driver.go.entity.QuestionInfo;
import com.driver.go.http.RetrofitHttpRequest;
import com.driver.go.http.SubscriberOnNextListener;
import com.driver.go.utils.Logger;

import java.util.List;


/**
 * Created by Administrator on 2016/12/1.
 */
public class PracticeOrderActivity extends DriverBaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_practise);
        initView();
        initData();

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        initDB();
    }

    private void initDB() {
        SQLiteManager.getInstance().createTables();
    }

    private void getQuestionData(){
        RetrofitHttpRequest.getInstance().getC1Subject1OrderQuestions(new SubscriberOnNextListener<List<QuestionInfo>>(){
            @Override
            public void onNext(List<QuestionInfo> questionInfos) {
                Log.d("MLJ","questionInfos==" + questionInfos);
            }
        });
    }
}
