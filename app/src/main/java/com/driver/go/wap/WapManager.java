package com.driver.go.wap;

import android.content.Context;

import com.driver.go.base.DriverGoApplication;
import com.driver.go.base.Profile;
import com.driver.go.utils.Logger;
import com.driver.go.utils.ToastManager;
import com.wanpu.pay.PayConnect;
import com.wanpu.pay.PayResultListener;

import cn.waps.AppConnect;

/**
 * Created by malijie on 2017/3/23.
 */

public class WapManager {
    public static WapManager sWapManager = null;
    private Context mContext = null;
    private AppConnect mAppConnect = null;

    private WapManager(Context context){
        mContext = context;
        mAppConnect = AppConnect.getInstance(WapProfile.WAP_APP_ID,WapProfile.WAP_APP_PID,mContext);
        mAppConnect.initUninstallAd(mContext);
    }

    public static WapManager getInstance(Context context){
        if(sWapManager == null){
            synchronized (WapManager.class){
                if(sWapManager == null){
                    sWapManager = new WapManager(context);
                }
            }
        }
        return sWapManager;
    }



    public void close(){
//        Logger.mlj("wap close...");
//        mAppConnect.releaseUninstallAd(mContext);
//        mAppConnect.close();
    }

    public void feedbackApp(){
        mAppConnect.showFeedback(mContext);
    }


    public void updateApp(){
        mAppConnect.checkUpdate(mContext);
    }

}
