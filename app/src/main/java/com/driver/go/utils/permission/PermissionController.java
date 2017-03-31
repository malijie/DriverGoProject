package com.driver.go.utils.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.driver.go.utils.Logger;
import com.driver.go.utils.ToastManager;
import com.driver.go.wap.WapManager;

import java.util.ArrayList;
import java.util.List;

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

    public static void initPermission(Activity activity) {
        List<String> permissionsNeeded = new ArrayList<String>();
        final List<String> permissionsList = new ArrayList<String>();
        if (!addPermission(activity,permissionsList, Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsNeeded.add("GPS");
        if (!addPermission(activity,permissionsList, Manifest.permission.READ_PHONE_STATE))
            permissionsNeeded.add("Read Phone");
        if (!addPermission(activity,permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            permissionsNeeded.add("Write Storage");

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = "You need to grant access to " + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);
                ActivityCompat.requestPermissions(activity,permissionsList.toArray(new String[permissionsList.size()]),
                        RESULT_CODE);
                return;
            }
            ActivityCompat.requestPermissions(activity,permissionsList.toArray(new String[permissionsList.size()]),
                    RESULT_CODE);
            return;
        }
        WapManager.getInstance(activity);
    }

    private static boolean addPermission(Activity activity, List<String> permissionsList, String permission) {
        if (ContextCompat.checkSelfPermission(activity,permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity,permission))
                return false;
        }
        return true;
    }

}
