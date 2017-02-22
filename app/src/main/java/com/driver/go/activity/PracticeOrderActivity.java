package com.driver.go.activity;

import android.os.Bundle;
import android.util.Log;

import com.driver.go.entity.QuestionInfo;
import com.driver.go.http.RetrofitHttpRequest;

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
        Log.d("MLJ","HAHAHAH ");
        RetrofitHttpRequest.getInstatnce().getOrderQuestions(1, "c1", "rand", new Subscriber<List<QuestionInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<QuestionInfo> o) {
                Log.d("MLJ","o=" + o.get(0).getUrl());
            }
        });

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
