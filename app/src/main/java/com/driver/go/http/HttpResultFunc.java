package com.driver.go.http;

import com.driver.go.entity.HttpResultBean;

import rx.functions.Func1;

/**
 * Created by Administrator on 2017/2/22.
 */

public class HttpResultFunc<T> implements Func1<HttpResultBean<T>,T>{

    @Override
    public T call(HttpResultBean<T> httpResultBean) {
        if(!httpResultBean.getReason().equals("0")){
            new ApiException(httpResultBean.getReason());
        }
        return httpResultBean.getResult();
    }
}
