package com.driver.go.http;


import com.driver.go.base.Profile;
import com.driver.go.entity.QuestionItem;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
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

    public static RetrofitHttpRequest getInstance(){
        if(sRetrofitRequest == null){
            synchronized (sObject){
                if(sRetrofitRequest == null){
                    sRetrofitRequest = new RetrofitHttpRequest();
                }
            }
        }
        return sRetrofitRequest;
    }

    public void getC1Subject1OrderQuestions(Subscriber subscriber){
        Observable observable = sRetrofitService.getQuestions(
                Profile.DRIVER_SUBJECT_ONE, Profile.DRIVER_MODEL_ONE,
                Profile.APP_KEY, Profile.TEST_TYPE_ORDER)
                .map(new HttpResultFunc<List<QuestionItem>>());
        toSubscribe(observable,subscriber);
    }

    public void getC1Subject4OrderQuestions(Subscriber subscriber){
        Observable observable = sRetrofitService.getQuestions(
                Profile.DRIVER_SUBJECT_FOUR, Profile.DRIVER_MODEL_ONE,
                Profile.APP_KEY, Profile.TEST_TYPE_ORDER)
                .map(new HttpResultFunc<List<QuestionItem>>());
        toSubscribe(observable,subscriber);
    }

    public void getC1Subject1RandomQuestions(Subscriber subscriber){
        Observable observable = sRetrofitService.getQuestions(
                Profile.DRIVER_SUBJECT_ONE, Profile.DRIVER_MODEL_ONE,
                Profile.APP_KEY, Profile.TEST_TYPE_RANDOM)
                .map(new HttpResultFunc<List<QuestionItem>>());
        toSubscribe(observable,subscriber);
    }

    public void getC1Subject4RandomQuestions(Subscriber subscriber){
        Observable observable = sRetrofitService.getQuestions(
                Profile.DRIVER_SUBJECT_FOUR, Profile.DRIVER_MODEL_ONE,
                Profile.APP_KEY, Profile.TEST_TYPE_RANDOM)
                .map(new HttpResultFunc<List<QuestionItem>>());
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
