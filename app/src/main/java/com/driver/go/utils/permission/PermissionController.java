package com.driver.go.utils.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.driver.go.utils.Logger;
import com.driver.go.utils.ToastManager;

/**
 * Created by malijie on 2017/3/27.
 */

public class PermissionController {
    public static final int RESULT_CODE = 1;

    public static boolean checkPermission(Activity activity, String permission){
        if(ContextCompat.checkSelfPermission(activity,permission) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,new String[]{permission},RESULT_CODE);
            return false;
        }
        return true;
    }

}
