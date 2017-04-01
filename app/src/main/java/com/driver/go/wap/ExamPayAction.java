package com.driver.go.wap;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import com.driver.go.base.Profile;
import com.driver.go.utils.Logger;
import com.driver.go.utils.ToastManager;
import com.driver.go.utils.Util;
import com.driver.go.utils.permission.PermissionController;
import com.wanpu.pay.PayConnect;
import com.wanpu.pay.PayResultListener;

/**
 * Created by malijie on 2017/3/27.
 */

public class ExamPayAction extends PayBaseAction implements IPayAction {
    private Activity mActivity;
    public ExamPayAction(Activity activity){
        super(activity);
        mActivity = activity;
    }

    @Override
    public void pay() {
        String userId = mPayConnect.getDeviceId(mActivity);
        String orderId = String.valueOf(System.currentTimeMillis());
        mPayConnect.pay(mActivity, orderId, userId, PRICE_EXAM,
                GOODS_NAME_EXAM, GOODS_DESCR_EXAM, "",new MyPayResultListener());
    }

    private class MyPayResultListener implements PayResultListener {

        @Override
        public void onPayFinish(Context payViewContext, String orderId,
                                int resultCode, String resultString, int payType, float amount,
                                String goodsName) {
            // 可根据resultCode自行判断
            if (resultCode == 0) {
                ToastManager.showShortMsg("购买成功");
                // 支付成功时关闭当前支付界面
                PayConnect.getInstance(mActivity).closePayView(payViewContext);

                // TODO 在客户端处理支付成功的操作

                // 未指定notifyUrl的情况下，交易成功后，必须发送回执
                PayConnect.getInstance(mActivity).confirm(orderId,payType);
            } else {
                ToastManager.showShortMsg("购买失败");
            }
        }
    }
}
