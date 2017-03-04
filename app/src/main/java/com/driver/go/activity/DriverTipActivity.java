package com.driver.go.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.driver.go.R;
import com.driver.go.base.Profile;

/**
 * Created by Administrator on 2017/3/4.
 */

public class DriverTipActivity extends DriverBaseActivity{
    private TextView mTextContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_tip);
        initView();
        initData();
    }

    @Override
    public void initView() {
        mTextContent = (TextView) findViewById(R.id.id_driver_tip_text_content);
    }

    @Override
    public void initData() {
        mTextContent.setText(Profile.DRIVER_TIP);
    }
}
