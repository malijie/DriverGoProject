package com.driver.go.wap;

import android.app.Activity;
import android.content.Context;


import com.driver.go.utils.Logger;

import cn.waps.AppConnect;

/**
 * Created by malijie on 2017/3/23.
 */

public class WapManager {
    public static WapManager sWapManager = null;
    private AppConnect mAppConnect = null;

    private WapManager(Context context){
        mAppConnect = AppConnect.getInstance(context);
        mAppConnect.initUninstallAd(context);
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



    public void close(Context context){
        mAppConnect.releaseUninstallAd(context);
        mAppConnect.close();
    }

    public void feedbackApp(Context context){
        mAppConnect.showFeedback(context);
    }


    public void updateApp(Context context){
        mAppConnect.checkUpdate(context);
    }

}
