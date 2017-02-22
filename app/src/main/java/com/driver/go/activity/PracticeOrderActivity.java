package com.driver.go.activity;

import android.os.Bundle;
import android.util.Log;

import com.driver.go.base.DriverExamData;
import com.driver.go.entity.QuestionInfo;
import com.driver.go.http.RetrofitHttpRequest;
import com.driver.go.http.SubscriberOnNextListener;

import java.util.List;

import rx.Subscriber;

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

    }

    private void getQuestionData(){
        RetrofitHttpRequest.getInstance().getSubject1C1OrderQuestions(new SubscriberOnNextListener<List<QuestionInfo>>(){
            @Override
            public void onNext(List<QuestionInfo> questionInfos) {
                Log.d("MLJ","questionInfos==" + questionInfos);
            }
        });
    }
}
