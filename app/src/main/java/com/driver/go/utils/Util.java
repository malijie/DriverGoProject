package com.driver.go.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;

import com.driver.go.base.DriverGoApplication;
import com.driver.go.base.Profile;
import com.driver.go.db.DBConstants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static void copyDB2Phone(){
        String dbFileName = DBConstants.DB_PATH + DBConstants.DB_NAME;
        File dbFile = new File(dbFileName);
        InputStream is = null;
        FileOutputStream os = null;
        if(!dbFile.exists()){
            dbFile.getParentFile().mkdirs();
            try{
                os = new FileOutputStream(dbFileName);
                is = DriverGoApplication.sContext.getResources().getAssets().open(DBConstants.DB_NAME);
                byte[] buffer = new byte[1024];
                int count = 0;
                while((count=is.read(buffer))>0){
                    os.write(buffer, 0, count);
                    os.flush();
                }
            }catch(FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    is.close();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getResString(int resId){
        return DriverGoApplication.sContext.getResources().getString(resId);
    }

    public static View getView(int resId){
        return LayoutInflater.from(DriverGoApplication.sContext).inflate(resId,null);
    }

    public static String getCurrentDate(){
        return  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
    }


    public static int getColor(int resColor){
        return DriverGoApplication.sContext.getResources().getColor(resColor);
    }

}
