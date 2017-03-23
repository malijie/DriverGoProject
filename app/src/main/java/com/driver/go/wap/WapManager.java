package com.driver.go.wap;

import android.content.Context;

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
        mAppConnect.getInstance(WapProfile.WAP_APP_ID,WapProfile.WAP_APP_PID,context);
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
        mAppConnect.close();
    }

    public void feedbackApp(){
        mAppConnect.showFeedback(mContext);
    }


    public void updateApp(){
        mAppConnect.checkUpdate(mContext);
    }

}
