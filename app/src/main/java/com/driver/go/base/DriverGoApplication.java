package com.driver.go.base;

import android.app.Application;
import android.content.Context;

import com.wanpu.pay.PayConnect;

/**
 * Created by malijie on 2016/12/1.
 */
public class DriverGoApplication extends Application{
    public static Context sContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();

    }
}
