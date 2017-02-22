package com.driver.go.http;

import com.driver.go.utils.ToastManager;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/2/22.
 */

public class SubscriberOnNextListener<T> extends Subscriber<T>{
    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        ToastManager.showShortMsg("数据返回失败，请检查网络连接");
    }

    @Override
    public void onNext(T t) {

    }
}
