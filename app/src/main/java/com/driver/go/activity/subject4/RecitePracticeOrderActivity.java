package com.driver.go.activity.subject4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.driver.go.R;
import com.driver.go.activity.DriverBaseActivity;
import com.driver.go.base.Profile;
import com.driver.go.control.EntityConvertManager;
import com.driver.go.entity.QuestionItem;
import com.driver.go.utils.ToastManager;
import com.driver.go.utils.Util;


/**
 * Created by Administrator on 2016/12/1.
 */
public class RecitePracticeOrderActivity extends DriverBaseActivity implements View.OnClickListener{

    private ImageButton mButtonBack;
    private TextView mTextNum;
    private LinearLayout mLayoutDetailExplain;
    private LinearLayout mLayoutExclude;
    private LinearLayout mLayoutExplain;
    private LinearLayout mLayoutCollect;
    private RelativeLayout mLayoutChoiceA;
    private RelativeLayout mLayoutChoiceB;
    private RelativeLayout mLayoutChoiceC;
    private RelativeLayout mLayoutChoiceD;
    private ImageView mImageChoiceA;
    private ImageView mImageChoiceB;
    private ImageView mImageChoiceC;
    private ImageView mImageChoiceD;
    private ImageButton mButtonCollect;
    private ImageButton mButtonExplain;
    private ImageButton mButtonExclude;
    private ImageView mImageItem;
    private ImageView mImageQuestion;
    private TextView mTextTitle;
    private TextView mTextChoiceA;
    private TextView mTextChoiceB;
    private TextView mTextChoiceC;
    private TextView mTextChoiceD;
    private TextView mTextExplain;
    private Button mButtonNext;
    private Button mButtonPre;

    private int mCurrentId = 1;
    private QuestionItem mCurrentQuestionItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recite_order_practise);
        initData();
        initView();
    }

    @Override
    public void initView() {

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver,filter);

        mButtonBack = (ImageButton) findViewById(R.id.id_question_title_button_back);
        mImageQuestion = (ImageView) findViewById(R.id.id_recite_order_practice_image_question);
        mTextNum = (TextView) findViewById(R.id.id_question_title_text_num);
        mLayoutDetailExplain = (LinearLayout) findViewById(R.id.id_recite_order_practice_layout_explain);
        mLayoutExclude = (LinearLayout) findViewById(R.id.id_question_title_layout_exclude);
        mLayoutCollect = (LinearLayout) findViewById(R.id.id_question_title_layout_collect);
        mLayoutExplain = (LinearLayout) findViewById(R.id.id_question_title_layout_explain);
        mLayoutChoiceA = (RelativeLayout) findViewById(R.id.id_recite_order_practice_layout_choice_a);
        mLayoutChoiceB = (RelativeLayout) findViewById(R.id.id_recite_order_practice_layout_choice_b);
        mLayoutChoiceC = (RelativeLayout) findViewById(R.id.id_recite_order_practice_layout_choice_c);
        mLayoutChoiceD = (RelativeLayout) findViewById(R.id.id_recite_order_practice_layout_choice_d);
        mImageChoiceA = (ImageView) findViewById(R.id.id_recite_order_practice_image_choice_a);
        mImageChoiceB = (ImageView) findViewById(R.id.id_recite_order_practice_image_choice_b);
        mImageChoiceC = (ImageView) findViewById(R.id.id_recite_order_practice_image_choice_c);
        mImageChoiceD = (ImageView) findViewById(R.id.id_recite_order_practice_image_choice_d);
        mButtonCollect = (ImageButton) findViewById(R.id.id_question_title_button_collect);
        mButtonExplain = (ImageButton) findViewById(R.id.id_question_title_button_explain);
        mButtonExclude = (ImageButton) findViewById(R.id.id_question_title_button_exclude);
        mTextExplain = (TextView) findViewById(R.id.id_recite_order_practice_text_explain);
        mImageItem = (ImageView) findViewById(R.id.id_recite_order_practice_image_item);
        mTextTitle = (TextView) findViewById(R.id.id_recite_order_practice_text_title);
        mTextChoiceA= (TextView) findViewById(R.id.id_recite_order_practice_text_choice_a);
        mTextChoiceB= (TextView) findViewById(R.id.id_recite_order_practice_text_choice_b);
        mTextChoiceC= (TextView) findViewById(R.id.id_recite_order_practice_text_choice_c);
        mTextChoiceD= (TextView) findViewById(R.id.id_recite_order_practice_text_choice_d);
        mButtonNext = (Button) findViewById(R.id.id_recite_order_practice_button_next);
        mButtonPre = (Button) findViewById(R.id.id_recite_order_practice_button_pre);

        mLayoutExclude.setVisibility(View.GONE);
        mLayoutCollect.setVisibility(View.GONE);
        mLayoutExplain.setVisibility(View.GONE);
        mButtonBack.setOnClickListener(this);
        mLayoutDetailExplain.setOnClickListener(this);
        mButtonNext.setOnClickListener(this);
        mButtonPre.setOnClickListener(this);

        mTextNum.setText(mCurrentId + "/" + sOrderQuestionTotalNum);
        mTextTitle.setText(mCurrentQuestionItem.getQuestion());
        mTextChoiceA.setText(mCurrentQuestionItem.getItem1());
        mTextChoiceB.setText(mCurrentQuestionItem.getItem2());
        mTextChoiceC.setText(mCurrentQuestionItem.getItem3());
        mTextChoiceD.setText(mCurrentQuestionItem.getItem4());
        mTextExplain.setText(mCurrentQuestionItem.getExplains());

        if(!TextUtils.isEmpty(mCurrentQuestionItem.getUrl())){
            mImageQuestion.setVisibility(View.VISIBLE);
            mImageLoader.showImage(mCurrentQuestionItem.getUrl(),mImageQuestion);
        }

        showRightAnswer(mCurrentQuestionItem.getAnswer());
    }

    @Override
    public void initData() {
        mCurrentId = loadReciteQuestionIndex(SUBJECT_TYPE_4);
        mCurrentQuestionItem = EntityConvertManager.getQuestionItemEntity(mSQLiteManager.queryOrderQuestionById(SUBJECT_TYPE_4,mCurrentId));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_question_title_button_back:
                finishActivity(this);
                break;

            case R.id.id_recite_order_practice_button_next:
                showNextQuestion();
                break;
            case R.id.id_recite_order_practice_button_pre:
                showPreQuestion();
                break;
        }
    }

    private void showRightAnswer(String answer) {
        if(answer.equals(ANSWER_A)){
            showRightAnswerImage(mImageChoiceA);
            return;
        }else if(answer.equals(ANSWER_B)){
            showRightAnswerImage(mImageChoiceB);
            return;
        }else if(answer.equals(ANSWER_C)){
            showRightAnswerImage(mImageChoiceC);
            return;
        }else if(answer.equals(ANSWER_D)){
            showRightAnswerImage(mImageChoiceD);
        }
    }

    private void showRightAnswerImage(ImageView imageView){
        imageView.setImageResource(R.mipmap.answer_right);
    }

    private void showWrongAnswerImage(ImageView imageView){
        imageView.setImageResource(R.mipmap.answer_wrong);
    }

    //下一题
    private void showNextQuestion() {
        if(hasInternet()){
            if(++mCurrentId> Profile.ORDER_TOTAL_ITEM){
                mCurrentId--;
                ToastManager.showCompleteReciteMsg();
                return;
            }
            //没有进行选择
            initUI();
            mCurrentQuestionItem = EntityConvertManager.getQuestionItemEntity(mSQLiteManager.queryOrderQuestionById(SUBJECT_TYPE_4,mCurrentId));
            saveReciteQuestionIndex(SUBJECT_TYPE_4,mCurrentId);
            updateUI(mCurrentQuestionItem);
        }else{
            ToastManager.showNoNetworkMsg();
        }

    }

    //上一题
    private void showPreQuestion(){
        if(hasInternet()) {
            if(--mCurrentId< 1){
                mCurrentId++;
                ToastManager.showLongMsg(getString(R.string.no_pre_order_question));
                return;
            }
            initUI();
            mCurrentQuestionItem = EntityConvertManager.getQuestionItemEntity(mSQLiteManager.queryOrderQuestionById(SUBJECT_TYPE_4,mCurrentId));
            saveReciteQuestionIndex(SUBJECT_TYPE_4,mCurrentId);
            updateUI(mCurrentQuestionItem);
        }else{
            ToastManager.showNoNetworkMsg();
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


    private void initUI(){
        mButtonCollect.setBackgroundResource(R.mipmap.icon_examin_shoucang);
        mImageChoiceA.setImageResource(R.mipmap.choice_a);
        mImageChoiceB.setImageResource(R.mipmap.choice_b);
        mImageChoiceC.setImageResource(R.mipmap.choice_c);
        mImageChoiceD.setImageResource(R.mipmap.choice_d);
        setAllAnswerSelect();
    }

    private void updateUI(QuestionItem item){
        mTextNum.setText(mCurrentId + "/" + sOrderQuestionTotalNum);
        mTextTitle.setText(item.getQuestion());
        mTextChoiceA.setText(item.getItem1());
        mTextChoiceB.setText(item.getItem2());
        mTextChoiceC.setText(item.getItem3());
        mTextChoiceD.setText(item.getItem4());
        mTextExplain.setText(item.getExplains());
        //设置题目图片
        if(!TextUtils.isEmpty(item.getUrl())){
            mImageQuestion.setVisibility(View.VISIBLE);
            mImageLoader.showImage(item.getUrl(),mImageQuestion);
        }

        //设置题目类型
        if(TextUtils.isEmpty(item.getItem3())){
            mImageItem.setImageResource(R.mipmap.judge_item);
            mLayoutChoiceC.setVisibility(View.GONE);
            mLayoutChoiceD.setVisibility(View.GONE);
        }else{
            mImageItem.setImageResource(R.mipmap.single_choice);
            mLayoutChoiceC.setVisibility(View.VISIBLE);
            mLayoutChoiceD.setVisibility(View.VISIBLE);
        }

        showRightAnswer(item.getAnswer());
    }

    public BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                if (Util.hasInternet()) {
                    initData();
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
