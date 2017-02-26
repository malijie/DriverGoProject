package com.driver.go.utils;

import android.widget.Toast;

import com.driver.go.base.DriverGoApplication;

import java.sql.Driver;

/**
 * Created by Administrator on 2017/2/22.
 */

public class ToastUtil {
    private static Toast sToast = null;

    public static void showMsg(String msg,int during){
        if(sToast != null){
            sToast.setText(msg);
            sToast.setDuration(during);
            sToast.show();
        }else{
            sToast = Toast.makeText(DriverGoApplication.sContext,msg,during);
            sToast.show();
        }
    }
}
