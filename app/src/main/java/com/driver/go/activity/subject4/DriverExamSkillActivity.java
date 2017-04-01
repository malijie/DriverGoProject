package com.driver.go.activity.subject4;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.driver.go.R;
import com.driver.go.activity.base.DriverBaseActivity;
import com.driver.go.base.Profile;

/**
 * Created by Administrator on 2017/3/4.
 */

public class DriverExamSkillActivity extends DriverBaseActivity implements View.OnClickListener{
    private TextView mTextTitle;
    private ImageButton mButtonBack;
    private TextView mTextContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_skill);
        initView();
        initData();
    }

    public void initView() {
        mButtonBack = (ImageButton) findViewById(R.id.id_title_bar_button_back);
        mTextTitle = (TextView) findViewById(R.id.id_driver_skill_text_title);
        mTextContent = (TextView) findViewById(R.id.id_driver_skill_text_content);
        mButtonBack.setOnClickListener(this);
    }

    public void initData() {
        mTextTitle.setText("科目四考试技巧记忆口诀");
        mTextContent.setText(Profile.SUBJECT4_DRIVER_SKILL);
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
