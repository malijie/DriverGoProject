package com.driver.go.wap;


import android.app.Activity;

import com.wanpu.pay.PayConnect;

import cn.waps.AppConnect;

/**
 * Created by malijie on 2017/3/27.
 */

public class PayBaseAction {
    protected PayConnect mPayConnect = null;
    public static final String GOODS_NAME_EXAM = "模拟考试";
    public static final String GOODS_DESCR_EXAM = "购买VIP会员，享受真题模拟考试服务";
    public static final float PRICE_EXAM = 4.99F;

    public static final String GOODS_NAME_VIP = "VIP会员";
    public static final String GOODS_DESCR_VIP = "购买VIP会员，享受查看真题解释服务";
    public static final float PRICE_VIP = 4.99F;

    public PayBaseAction(Activity activity){
        mPayConnect = PayConnect.getInstance(WapProfile.WAP_APP_ID,WapProfile.WAP_APP_PID,activity);
    }
}
