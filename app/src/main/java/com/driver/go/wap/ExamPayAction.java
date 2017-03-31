package com.driver.go.wap;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import com.driver.go.utils.Logger;
import com.driver.go.utils.ToastManager;
import com.driver.go.utils.permission.PermissionController;
import com.wanpu.pay.PayConnect;
import com.wanpu.pay.PayResultListener;

/**
 * Created by malijie on 2017/3/27.
 */

public class ExamPayAction extends PayBaseAction implements IPayAction {
    private PayConnect mPayConnect = null;
    private Activity mActivity;
    public ExamPayAction(Activity activity){
        mActivity = activity;
        mPayConnect = PayConnect.getInstance(activity);
    }

    @Override
    public void pay() {
        if(PermissionController.checkPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
Logger.mlj("ExamPayAction WapManager.getInstance(this)===" + WapManager.getInstance(mActivity));
            WapManager.getInstance(mActivity).payForExam(PRICE_EXAM, GOODS_NAME_EXAM, GOODS_DESCR_EXAM, new PayResultListener() {
                @Override
                public void onPayFinish(Context payViewContext,String orderId,int resultCode,
                                        String resultString, int payType, float amount, String goodsName) {
                    if(resultCode == 0){ // 支付成功
                        ToastManager.showShortMsg(resultString + "：" + amount + "元");
                        // 支付成功时关闭当前支付界面
                        mPayConnect.closePayView(payViewContext);
                        // TODO 在客户端处理支付成功的操作

                        // 未指定notifyUrl的情况下，交易成功后，必须发送回执
                        mPayConnect.confirm(orderId, payType);
                    }else{// 支付失败
                        ToastManager.showShortMsg(resultString);
                    }
                }
            });
        }
    }
}
