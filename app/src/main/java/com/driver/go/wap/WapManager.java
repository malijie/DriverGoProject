package com.driver.go.wap;

import android.content.Context;

import com.driver.go.base.DriverGoApplication;
import com.driver.go.base.Profile;
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
    private PayConnect mPayConnect = null;

    private WapManager(Context context){
        mContext = context;
        mAppConnect = mAppConnect.getInstance(WapProfile.WAP_APP_ID,WapProfile.WAP_APP_PID,mContext);
        mPayConnect = PayConnect.getInstance(WapProfile.WAP_APP_ID, WapProfile.WAP_APP_PID, context);

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
        mAppConnect.close();
    }

    public void feedbackApp(){
        mAppConnect.showFeedback(mContext);
    }


    public void updateApp(){
        mAppConnect.checkUpdate(mContext);
    }

    public String getUserId(){
       return mPayConnect.getDeviceId(mContext);
    }

    //PayConnect.getInstance(context).pay(context,
    //orderId, userId, price, goodsName, goodsDesc, notifyUrl, new MyPayResultListener());
    public void payForExam(float price,String goodsName,String goodsDesc,PayResultListener resultListener){
        mPayConnect.pay(mContext, getOrderId(), DriverGoApplication.mDeviceId, price, goodsName, goodsDesc, "" ,resultListener);
    }

    private String getOrderId(){
        return String.valueOf(System.currentTimeMillis() );
    }

    public class MyPayResultListener implements PayResultListener {
        @Override
        public void onPayFinish(Context payViewContext,String order_id,int resultCode, String resultString, int payType, float amount, String goods_name) {
            if(resultCode == 0){ // 支付成功
                ToastManager.showShortMsg(resultString + "：" + amount + "元");
                // 支付成功时关闭当前支付界面
                mPayConnect.closePayView(payViewContext);
                // TODO 在客户端处理支付成功的操作

                // 未指定notifyUrl的情况下，交易成功后，必须发送回执
                mPayConnect.confirm(order_id, payType);
            }else{// 支付失败
                ToastManager.showShortMsg(resultString);
            }
        }
    }
}
