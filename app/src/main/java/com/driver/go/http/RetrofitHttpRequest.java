package com.driver.go.http;


import com.driver.go.entity.HttpResultBean;
import com.driver.go.entity.QuestionInfo;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/22.
 */

public class RetrofitHttpRequest {
    private static RetrofitHttpRequest sRetrofitRequest = null;
    private static final Object sObject = new Object();
    private RetrofitService sRetrofitService = null;
    private RetrofitHttpRequest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLContainer.getBaseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        sRetrofitService = retrofit.create(RetrofitService.class);
    }

    public static RetrofitHttpRequest getInstatnce(){
        if(sRetrofitRequest == null){
            synchronized (sObject){
                if(sRetrofitRequest == null){
                    sRetrofitRequest = new RetrofitHttpRequest();
                }
            }
        }
        return sRetrofitRequest;
    }

    public void getOrderQuestions(int subject,String model,String testType,Subscriber subscriber){
        Observable observable = sRetrofitService.getQuestions(subject,model,URLContainer.getAppKey(),testType)
                .map(new HttpResultFunc<List<QuestionInfo>>());
        toSubscribe(observable,subscriber);
    }

    private void toSubscribe(Observable observable, Subscriber subscriber){
        if(observable != null && subscriber != null){
            observable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(subscriber);
        }
    }

}
