package com.driver.go.activity;

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
import com.driver.go.base.Profile;
import com.driver.go.control.EntityConvertManager;
import com.driver.go.entity.QuestionItem;
import com.driver.go.utils.ToastManager;
import com.driver.go.utils.Util;


/**
 * Created by Administrator on 2016/12/1.
 */
public class PracticeOrderActivity extends DriverBaseActivity implements View.OnClickListener{

    private ImageButton mButtonBack;
    private TextView mTextNum;
    private LinearLayout mLayoutExculde;
    private LinearLayout mLayoutTitleExplain;
    private LinearLayout mLayoutDetailExplain;
    private RelativeLayout mLayoutChoiceA;
    private RelativeLayout mLayoutChoiceB;
    private RelativeLayout mLayoutChoiceC;
    private RelativeLayout mLayoutChoiceD;
    private ImageView mImageChoiceA;
    private ImageView mImageChoiceB;
    private ImageView mImageChoiceC;
    private ImageView mImageChoiceD;

    private ImageView mImageItem;
    private ImageView mImageQuestion;
    private TextView mTextTitle;
    private TextView mTextChoiceA;
    private TextView mTextChoiceB;
    private TextView mTextChoiceC;
    private TextView mTextChoiceD;
    private TextView mTextExplain;
    private Button mButtonPre;
    private Button mButtonNext;

    private int mCurrentId = 1;
    private QuestionItem mCurrentQuestionItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_practise);
        initData();
        initView();
    }

    @Override
    public void initView() {
        mButtonBack = (ImageButton) findViewById(R.id.id_question_title_button_back);
        mImageQuestion = (ImageView) findViewById(R.id.id_order_practice_image_question);
        mTextNum = (TextView) findViewById(R.id.id_question_title_text_num);
        mLayoutExculde = (LinearLayout) findViewById(R.id.id_question_title_layout_exclude);
        mLayoutTitleExplain = (LinearLayout) findViewById(R.id.id_question_title_layout_explain);
        mLayoutDetailExplain = (LinearLayout) findViewById(R.id.id_order_practice_layout_explain);
        mLayoutChoiceA = (RelativeLayout) findViewById(R.id.id_order_practice_layout_choice_a);
        mLayoutChoiceB = (RelativeLayout) findViewById(R.id.id_order_practice_layout_choice_b);
        mLayoutChoiceC = (RelativeLayout) findViewById(R.id.id_order_practice_layout_choice_c);
        mLayoutChoiceD = (RelativeLayout) findViewById(R.id.id_order_practice_layout_choice_d);
        mImageChoiceA = (ImageView) findViewById(R.id.id_order_practice_image_choice_a);
        mImageChoiceB = (ImageView) findViewById(R.id.id_order_practice_image_choice_b);
        mImageChoiceC = (ImageView) findViewById(R.id.id_order_practice_image_choice_c);
        mImageChoiceD = (ImageView) findViewById(R.id.id_order_practice_image_choice_d);
        mTextExplain = (TextView) findViewById(R.id.id_order_practice_text_explain);
        mImageItem = (ImageView) findViewById(R.id.id_order_practice_image_item);
        mTextTitle = (TextView) findViewById(R.id.id_order_practice_text_title);
        mTextChoiceA= (TextView) findViewById(R.id.id_order_practice_text_choice_a);
        mTextChoiceB= (TextView) findViewById(R.id.id_order_practice_text_choice_b);
        mTextChoiceC= (TextView) findViewById(R.id.id_order_practice_text_choice_c);
        mTextChoiceD= (TextView) findViewById(R.id.id_order_practice_text_choice_d);
        mButtonPre = (Button) findViewById(R.id.id_order_practice_button_previous);
        mButtonNext = (Button) findViewById(R.id.id_order_practice_button_next);

        mButtonBack.setOnClickListener(this);
        mLayoutExculde.setOnClickListener(this);
        mLayoutTitleExplain.setOnClickListener(this);
        mLayoutDetailExplain.setOnClickListener(this);
        mButtonPre.setOnClickListener(this);
        mButtonNext.setOnClickListener(this);
        mLayoutChoiceA.setOnClickListener(this);
        mLayoutChoiceB.setOnClickListener(this);
        mLayoutChoiceC.setOnClickListener(this);
        mLayoutChoiceD.setOnClickListener(this);

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
    }

    @Override
    public void initData() {
        mCurrentQuestionItem = EntityConvertManager.getQuestionItemEntity(mSQLiteManager.queryOrderQuestionById(1));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_question_title_button_back:
                finishActivity(this);
                break;

            case R.id.id_question_title_layout_exclude:

                break;
            case R.id.id_question_title_layout_explain:
                showExplain();
                break;

            case R.id.id_order_practice_button_previous:
                showPreQuestion();
                break;

            case R.id.id_order_practice_button_next:
                showNextQuestion();
                break;

            case R.id.id_order_practice_layout_choice_a:
                checkAnswer(ANSWER_A,mImageChoiceA);
                break;

            case R.id.id_order_practice_layout_choice_b:
                checkAnswer(ANSWER_B,mImageChoiceB);
                break;

            case R.id.id_order_practice_layout_choice_c:
                checkAnswer(ANSWER_C,mImageChoiceC);
                break;

            case R.id.id_order_practice_layout_choice_d:
                checkAnswer(ANSWER_D,mImageChoiceD);
                break;
        }
    }

    //检查答案
    private void checkAnswer(String answer,ImageView imageView) {
        if(mCurrentQuestionItem.getAnswer().equals(answer)){
            imageView.setImageResource(R.mipmap.answer_right);
        }else{
            imageView.setImageResource(R.mipmap.answer_wrong);
        }
    }

    //上一题
    private void showNextQuestion() {
        initUI();
        if(hasInternet()){
            if(++mCurrentId> Profile.ORDER_TOTAL_ITEM){
                mCurrentId--;
                ToastManager.showLongMsg(getString(R.string.complete_all_order_question));
                return;
            }
            mCurrentQuestionItem = EntityConvertManager.getQuestionItemEntity(mSQLiteManager.queryOrderQuestionById(mCurrentId));
            updateUI(mCurrentQuestionItem);
        }else{
            ToastManager.showLongMsg(getString(R.string.current_network_unavailable));
        }

    }

    //下一题
    private void showPreQuestion(){
        initUI();
        if(hasInternet()) {
            if(--mCurrentId< 1){
                mCurrentId++;
                ToastManager.showLongMsg(getString(R.string.no_pre_order_question));
                return;
            }
            mCurrentQuestionItem = EntityConvertManager.getQuestionItemEntity(mSQLiteManager.queryOrderQuestionById(mCurrentId));
            updateUI(mCurrentQuestionItem);
        }else{
            ToastManager.showLongMsg(getString(R.string.current_network_unavailable));
        }

    }
    //显示解释
    private void showExplain() {
        mLayoutDetailExplain.setVisibility(View.VISIBLE);
    }

    private void initUI(){
        mImageChoiceA.setImageResource(R.mipmap.choice_a);
        mImageChoiceB.setImageResource(R.mipmap.choice_b);
        mImageChoiceC.setImageResource(R.mipmap.choice_c);
        mImageChoiceD.setImageResource(R.mipmap.choice_d);
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
    }
}
