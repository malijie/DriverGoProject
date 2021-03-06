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

    public static void showCompelteRandomPracticeMsg(){
        ToastUtil.showMsg(DriverGoApplication.sContext.getString(R.string.complete_all_random_question), Toast.LENGTH_SHORT);
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

    public static void showCompleteReciteMsg(){
        ToastUtil.showMsg(DriverGoApplication.sContext.getString(R.string.complete_all_recite),Toast.LENGTH_SHORT);
    }

    public static void showNoNetworkMsg(){
        ToastUtil.showMsg(DriverGoApplication.sContext.getString(R.string.current_network_unavailable),Toast.LENGTH_SHORT);
    }

    public static void showCompleteWrongQuestionMsg(){
        ToastUtil.showMsg(DriverGoApplication.sContext.getString(R.string.complete_all_wrong_question),Toast.LENGTH_SHORT);
    }

    public static void showNoWrongQuestionMsg() {
        ToastUtil.showMsg(DriverGoApplication.sContext.getString(R.string.no_wrong_question),Toast.LENGTH_SHORT);
    }

    public static void showNoCollectQuestionMsg() {
        ToastUtil.showMsg(DriverGoApplication.sContext.getString(R.string.no_collect_question),Toast.LENGTH_SHORT);
    }

    public static void showNoExamWrongQuestionMsg() {
        ToastUtil.showMsg(DriverGoApplication.sContext.getString(R.string.no_exam_wrong_question),Toast.LENGTH_SHORT);
    }

    public static void showCompleteCollectQuestionMsg() {
        ToastUtil.showMsg(DriverGoApplication.sContext.getString(R.string.complete_all_collect_question),Toast.LENGTH_SHORT);
    }

    public static void showExitTipMsg(){
        ToastUtil.showMsg(DriverGoApplication.sContext.getString(R.string.exit_tip),Toast.LENGTH_SHORT);
    }

    public static void showAllowPermissionTip() {
        ToastUtil.showMsg(DriverGoApplication.sContext.getString(R.string.permission_allow_tip),Toast.LENGTH_LONG);
    }
}
