package com.driver.go.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.driver.go.base.DriverGoApplication;

/**
 * Created by malijie on 2017/2/24.
 */

public class Util {
    public static boolean hasInternet(){
        ConnectivityManager connectivity = (ConnectivityManager) DriverGoApplication.sContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()){
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getResString(int resId){
        return DriverGoApplication.sContext.getResources().getString(resId);
    }
}
