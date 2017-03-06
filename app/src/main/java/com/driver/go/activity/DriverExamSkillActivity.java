package com.driver.go.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.driver.go.R;
import com.driver.go.base.Profile;

/**
 * Created by Administrator on 2017/3/4.
 */

public class DriverExamSkillActivity extends DriverBaseActivity{
    private TextView mTextTitle;
    private TextView mTextContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_skill);
        initView();
        initData();
    }

    @Override
    public void initView() {
        mTextTitle = (TextView) findViewById(R.id.id_driver_skill_text_title);
        mTextContent = (TextView) findViewById(R.id.id_driver_skil_text_content);
    }

    @Override
    public void initData() {
        mTextContent.setText(Profile.DRIVER_SKILL);
    }
}
