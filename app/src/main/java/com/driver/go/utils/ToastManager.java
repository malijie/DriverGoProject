package com.driver.go.utils;

import android.widget.Toast;

import com.driver.go.R;
import com.driver.go.base.DriverGoApplication;

/**
 * Created by Administrator on 2017/2/22.
 */

public class ToastManager {
    public static void showLongMsg(String msg){
        ToastUtil.showMsg(msg, Toast.LENGTH_LONG);
    }

    public static void showShortMsg(String msg){
        ToastUtil.showMsg(msg, Toast.LENGTH_SHORT);
    }

    public static void showSelectOneAnswerMsg(){
        ToastUtil.showMsg(DriverGoApplication.sContext.getString(R.string.at_least_select_one_answer), Toast.LENGTH_SHORT);
    }

    public static void showCollectSuccessMsg(){
        ToastUtil.showMsg(DriverGoApplication.sContext.getString(R.string.collect_success), Toast.LENGTH_SHORT);
    }

    public static void showAlreadyCollectMsg(){
        ToastUtil.showMsg(DriverGoApplication.sContext.getString(R.string.already_collect), Toast.LENGTH_SHORT);
    }

    public static void showAlreadyExcludeMsg(){
        ToastUtil.showMsg(DriverGoApplication.sContext.getString(R.string.already_exclude), Toast.LENGTH_SHORT);
    }

}
