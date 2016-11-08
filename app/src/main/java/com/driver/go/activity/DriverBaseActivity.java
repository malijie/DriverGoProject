package com.driver.go.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Administrator on 2016/11/5.
 */
public abstract class DriverBaseActivity extends FragmentActivity {
    public abstract void initView();
    public abstract void initData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
