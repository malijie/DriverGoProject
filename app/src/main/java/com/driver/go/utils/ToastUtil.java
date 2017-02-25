package com.driver.go.utils;

import android.widget.Toast;

import com.driver.go.base.DriverGoApplication;

import java.sql.Driver;

/**
 * Created by Administrator on 2017/2/22.
 */

public class ToastUtil {
    public static void showMsg(String msg,int during){
        Toast.makeText(DriverGoApplication.sContext,msg,during).show();
    }
}
