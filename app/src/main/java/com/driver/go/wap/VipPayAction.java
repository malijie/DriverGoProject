package com.driver.go.wap;

import android.app.Activity;
import android.content.Context;

import com.driver.go.utils.SharePreferenceUtil;
import com.driver.go.utils.ToastManager;
import com.wanpu.pay.PayConnect;
import com.wanpu.pay.PayResultListener;

/**
 * Created by malijie on 2017/3/27.
 */

public class VipPayAction extends PayBaseAction implements IPayAction {
    private Activity mActivity;
    public VipPayAction(Activity activity){
        super(activity);
        mActivity = activity;
    }

    @Override
    public void pay() {
        String userId = mPayConnect.getDeviceId(mActivity);
        String orderId = String.valueOf(System.currentTimeMillis());
        mPayConnect.pay(mActivity, orderId, userId, PRICE_VIP,
                GOODS_NAME_VIP, GOODS_DESCR_VIP, "",new MyPayResultListener());
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

                SharePreferenceUtil.savePayedVIPStatus(true);
                // 未指定notifyUrl的情况下，交易成功后，必须发送回执
                PayConnect.getInstance(mActivity).confirm(orderId,payType);
            } else {
                ToastManager.showShortMsg("购买失败");
                SharePreferenceUtil.savePayedVIPStatus(false);

            }
        }
    }
}
