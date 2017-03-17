package com.driver.go.activity.subject4;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.driver.go.R;
import com.driver.go.activity.base.DriverBaseActivity;
import com.driver.go.activity.base.SubjectFourBaseActivity;
import com.driver.go.base.Profile;

/**
 * Created by Administrator on 2017/3/4.
 */

public class DriverTipActivity extends SubjectFourBaseActivity implements View.OnClickListener{
    private TextView mTextContent;
    private ImageButton mButtonBack;

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
        mButtonBack = (ImageButton) findViewById(R.id.id_title_bar_button_back);
        mButtonBack.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mTextContent.setText(Profile.DRIVER_TIP);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_title_bar_button_back:
                finishActivity(this);
                break;
        }
    }
}
