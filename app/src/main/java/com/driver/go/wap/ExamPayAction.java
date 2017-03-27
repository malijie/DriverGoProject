package com.driver.go.wap;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import com.driver.go.activity.subject1.PracticeOrderActivity;
import com.driver.go.utils.ToastManager;
import com.driver.go.utils.permission.PermissionController;

/**
 * Created by malijie on 2017/3/27.
 */

public class ExamPayAction extends PayBaseAction implements IPayAction {
    @Override
    public void pay(Activity context) {
        if(PermissionController.checkPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            ToastManager.showShortMsg("pay for exam");
        }
    }
}
