package com.driver.go.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.driver.go.R;
import com.driver.go.utils.Logger;
import com.driver.go.utils.ToastManager;

/**
 * Created by malijie on 2017/3/6.
 */

public class ExamMainActivity extends DriverBaseActivity{
    private static final int MSG_HANDLE_TIME = 0;
    private int mTimeSecond = 59;
    private int mTimeMinute = 44;
    private String mStrSecond = "";
    private String mStrMinute = "";

    private TextView mTextTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_layout);
        initView();
        initData();
    }

    @Override
    public void initView() {
        mTextTime = (TextView) findViewById(R.id.id_question_title_text_clock);
    }

    @Override
    public void initData() {
        initTime();

    }


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_HANDLE_TIME:
                    mTimeSecond = msg.arg1;
                    --mTimeSecond;
                    mTimeMinute = msg.arg2;

                    mStrSecond = mTimeSecond + "";
                    if(mTimeSecond == 0 && mTimeMinute == 0){
                        mStrSecond = "00";
                        mStrMinute = "00";
                        mTextTime.setText(mStrMinute + ":" + mStrSecond);
                        ToastManager.showShortMsg("结束");
                        return;
                    }

                    if(mTimeSecond <10){
                        mStrSecond = "0" + mTimeSecond;
                        Logger.d("mStrSecond=" + mStrSecond);

                    }

                    if(mTimeSecond <0 && mTimeMinute > 0){
                        mTimeMinute--;
                        mTimeSecond = 59;
                        mStrSecond = mTimeSecond + "";
                        mStrMinute = mTimeMinute + "";
                    }

                    if(mTimeMinute <10){
                        mStrMinute = "0" + mTimeMinute;
                    }else{
                        mStrMinute = "" + mTimeMinute;
                    }

                    mTextTime.setText(mStrMinute + ":" + mStrSecond);
                    msg = obtainMessage();
                    msg.what = MSG_HANDLE_TIME;
                    msg.arg1 = mTimeSecond;
                    msg.arg2 = mTimeMinute;
                    mHandler.sendMessageDelayed(msg,1000);

                    break;
            }
        }
    };
    private void initTime() {
        Message msg = Message.obtain();
        msg.what = MSG_HANDLE_TIME;
        msg.arg1 = mTimeSecond;
        msg.arg2 = mTimeMinute;
        mHandler.sendMessage(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(MSG_HANDLE_TIME);
    }
}
