package com.driver.go.wap;


import android.app.Activity;

import com.wanpu.pay.PayConnect;

import cn.waps.AppConnect;

/**
 * Created by malijie on 2017/3/27.
 */

public class PayBaseAction {
    protected PayConnect mPayConnect = null;
    protected static final String GOODS_NAME_EXAM = "模拟考试";
    protected static final String GOODS_DESCR_EXAM = "购买会员，享受真题模拟考试功能";
    protected static final float PRICE_EXAM = 4.99F;

    public PayBaseAction(Activity activity){
        mPayConnect = PayConnect.getInstance(WapProfile.WAP_APP_ID,WapProfile.WAP_APP_PID,activity);
    }
}
