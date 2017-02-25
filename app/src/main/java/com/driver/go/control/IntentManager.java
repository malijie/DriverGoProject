package com.driver.go.control;

import android.app.Activity;
import android.content.Intent;

import com.driver.go.base.DriverGoApplication;

/**
 * Created by Administrator on 2016/12/1.
 */
public class IntentManager {

    public static void startActivity(Class clazz){
        Intent i = new Intent(DriverGoApplication.sContext,clazz);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        DriverGoApplication.sContext.startActivity(i);
    }

    public static void finishActivity(Activity activity){
        if(activity != null){
            activity.finish();
        }
    }
}
