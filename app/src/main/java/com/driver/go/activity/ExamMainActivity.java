package com.driver.go.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.driver.go.R;
import com.driver.go.control.IntentManager;
import com.driver.go.entity.QuestionItem;
import com.driver.go.http.RetrofitHttpRequest;
import com.driver.go.http.SubscriberOnNextListener;
import com.driver.go.utils.Logger;
import com.driver.go.utils.ToastManager;

import java.util.List;

/**
 * Created by malijie on 2017/3/6.
 */

public class ExamMainActivity extends DriverBaseActivity implements View.OnClickListener{
    private RetrofitHttpRequest mRetrofitRequest = null;

    private static final int MSG_HANDLE_TIME = 0;
    private int mTimeSecond = 59;
    private int mTimeMinute = 44;
    private String mStrSecond = "";
    private String mStrMinute = "";
    private int mCurrentId = 1;
    private List<QuestionItem> mQuestions = null;

    private TextView mTextTime;
    private ImageButton mButtonBack;
    private ImageButton mButtonCollect;
    private ImageView mImageQuestionType;
    private ImageView mImageQuestionContent;
    private TextView mTitleCount;
    private TextView mTextTitle;
    private RelativeLayout mLayoutChoiceA;
    private RelativeLayout mLayoutChoiceB;
    private RelativeLayout mLayoutChoiceC;
    private RelativeLayout mLayoutChoiceD;
    private ImageView mImageChoiceA;
    private ImageView mImageChoiceB;
    private ImageView mImageChoiceC;
    private ImageView mImageChoiceD;
    private TextView mTextChoiceA;
    private TextView mTextChoiceB;
    private TextView mTextChoiceC;
    private TextView mTextChoiceD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_layout);
        initData();
        initView();
    }

    @Override
    public void initView() {
        mImageQuestionContent =  (ImageView) findViewById(R.id.id_exam_main_image_question);
        mTextTime = (TextView) findViewById(R.id.id_exam_title_text_clock);
        mTitleCount = (TextView) findViewById(R.id.id_exam_title_text_num);
        mButtonBack = (ImageButton) findViewById(R.id.id_exam_title_button_back);
        mButtonCollect = (ImageButton) findViewById(R.id.id_exam_title_button_collect);
        mImageQuestionType = (ImageView) findViewById(R.id.id_exam_main_image_item);
        mTextTitle = (TextView) findViewById(R.id.id_exam_main_text_title);
        mLayoutChoiceA = (RelativeLayout) findViewById(R.id.id_exam_main_layout_choice_a);
        mLayoutChoiceB = (RelativeLayout) findViewById(R.id.id_exam_main_layout_choice_b);
        mLayoutChoiceC = (RelativeLayout) findViewById(R.id.id_exam_main_layout_choice_c);
        mLayoutChoiceD = (RelativeLayout) findViewById(R.id.id_exam_main_layout_choice_d);
        mImageChoiceA = (ImageView) findViewById(R.id.id_exam_main_image_choice_a);
        mImageChoiceB = (ImageView) findViewById(R.id.id_exam_main_image_choice_b);
        mImageChoiceC = (ImageView) findViewById(R.id.id_exam_main_image_choice_c);
        mImageChoiceD = (ImageView) findViewById(R.id.id_exam_main_image_choice_d);
        mTextChoiceA= (TextView) findViewById(R.id.id_exam_main_text_choice_a);
        mTextChoiceB= (TextView) findViewById(R.id.id_exam_main_text_choice_b);
        mTextChoiceC= (TextView) findViewById(R.id.id_exam_main_text_choice_c);
        mTextChoiceD= (TextView) findViewById(R.id.id_exam_main_text_choice_d);

    }

    @Override
    public void initData() {
        mRetrofitRequest = RetrofitHttpRequest.getInstance();
        initTime();
        initQuestion();
    }

    private void initQuestion() {
        mRetrofitRequest.getC1Subject1RandomQuestions(new SubscriberOnNextListener<List<QuestionItem>>(){
            @Override
            public void onNext(List<QuestionItem> questionItems) {
                mQuestions = questionItems;
                updateUI(mQuestions.get(0));
            }
        });
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_exam_title_button_back:
                IntentManager.finishActivity(this);
                break;
            case R.id.id_exam_title_button_collect:

                break;
        }
    }

    private void setAllAnswerUnSelect(){
        mLayoutChoiceA.setClickable(false);
        mLayoutChoiceB.setClickable(false);
        mLayoutChoiceC.setClickable(false);
        mLayoutChoiceD.setClickable(false);
    }

    private void setAllAnswerSelect(){
        mLayoutChoiceA.setClickable(true);
        mLayoutChoiceB.setClickable(true);
        mLayoutChoiceC.setClickable(true);
        mLayoutChoiceD.setClickable(true);
    }

    private void updateUI(QuestionItem item){
        mTitleCount.setText(mCurrentId + "/" + sExamQuestionTotalNum);
        mTextTitle.setText(item.getQuestion());
        mTextChoiceA.setText(item.getItem1());
        mTextChoiceB.setText(item.getItem2());
        mTextChoiceC.setText(item.getItem3());
        mTextChoiceD.setText(item.getItem4());
        //设置题目图片
        if(!TextUtils.isEmpty(item.getUrl())){
            mImageQuestionContent.setVisibility(View.VISIBLE);
            mImageLoader.showImage(item.getUrl(),mImageQuestionContent);
        }else{
            mImageQuestionContent.setVisibility(View.GONE);
        }

        //设置题目类型
        if(TextUtils.isEmpty(item.getItem3())){
            mImageQuestionType.setImageResource(R.mipmap.judge_item);
            mLayoutChoiceC.setVisibility(View.GONE);
            mLayoutChoiceD.setVisibility(View.GONE);
        }else{
            mImageQuestionType.setImageResource(R.mipmap.single_choice);
            mLayoutChoiceC.setVisibility(View.VISIBLE);
            mLayoutChoiceD.setVisibility(View.VISIBLE);
        }
    }
}
