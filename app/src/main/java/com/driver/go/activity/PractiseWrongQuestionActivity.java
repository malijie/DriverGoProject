package com.driver.go.activity;

import android.database.Cursor;
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
import com.driver.go.control.EntityConvertManager;
import com.driver.go.entity.QuestionItem;
import com.driver.go.utils.ToastManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malijie on 2017/3/1.
 */

public class PractiseWrongQuestionActivity  extends DriverBaseActivity implements View.OnClickListener{

    private ImageButton mButtonBack;
    private ImageButton mButtonDelete;
    private TextView mTextNum;
    private LinearLayout mLayoutDetailExplain;
    private RelativeLayout mLayoutChoiceA;
    private RelativeLayout mLayoutChoiceB;
    private RelativeLayout mLayoutChoiceC;
    private RelativeLayout mLayoutChoiceD;
    private LinearLayout mLayoutDelete;
    private LinearLayout mLayoutExclude;
    private ImageView mImageChoiceA;
    private ImageView mImageChoiceB;
    private ImageView mImageChoiceC;
    private ImageView mImageChoiceD;
    private ImageButton mButtonCollect;
    private ImageButton mButtonExplain;
    private ImageView mImageItem;
    private ImageView mImageQuestion;
    private TextView mTextTitle;
    private TextView mTextChoiceA;
    private TextView mTextChoiceB;
    private TextView mTextChoiceC;
    private TextView mTextChoiceD;
    private TextView mTextExplain;
    private Button mButtonNext;
    private List<QuestionItem> mQuestions;

    private int mCurrentIndex = 0;
    private QuestionItem mCurrentQuestionItem;
    private boolean mIsChoiceOneAnswer;
    private int mQuestionsCount;
    private boolean mIsExcluded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wrong_practise);
        initData();
        initView();
    }

    @Override
    public void initView() {
        mButtonBack = (ImageButton) findViewById(R.id.id_question_title_button_back);
        mImageQuestion = (ImageView) findViewById(R.id.id_wrong_practice_image_question);
        mTextNum = (TextView) findViewById(R.id.id_question_title_text_num);
        mLayoutDetailExplain = (LinearLayout) findViewById(R.id.id_wrong_practice_layout_explain);
        mLayoutExclude = (LinearLayout) findViewById(R.id.id_question_title_layout_exclude);
        mLayoutDelete = (LinearLayout) findViewById(R.id.id_question_title_layout_delete);
        mLayoutChoiceA = (RelativeLayout) findViewById(R.id.id_wrong_practice_layout_choice_a);
        mLayoutChoiceB = (RelativeLayout) findViewById(R.id.id_wrong_practice_layout_choice_b);
        mLayoutChoiceC = (RelativeLayout) findViewById(R.id.id_wrong_practice_layout_choice_c);
        mLayoutChoiceD = (RelativeLayout) findViewById(R.id.id_wrong_practice_layout_choice_d);
        mImageChoiceA = (ImageView) findViewById(R.id.id_wrong_practice_image_choice_a);
        mImageChoiceB = (ImageView) findViewById(R.id.id_wrong_practice_image_choice_b);
        mImageChoiceC = (ImageView) findViewById(R.id.id_wrong_practice_image_choice_c);
        mImageChoiceD = (ImageView) findViewById(R.id.id_wrong_practice_image_choice_d);
        mButtonCollect = (ImageButton) findViewById(R.id.id_question_title_button_collect);
        mButtonExplain = (ImageButton) findViewById(R.id.id_question_title_button_explain);
        mTextExplain = (TextView) findViewById(R.id.id_wrong_practice_text_explain);
        mImageItem = (ImageView) findViewById(R.id.id_wrong_practice_image_item);
        mTextTitle = (TextView) findViewById(R.id.id_wrong_practice_text_title);
        mTextChoiceA= (TextView) findViewById(R.id.id_wrong_practice_text_choice_a);
        mTextChoiceB= (TextView) findViewById(R.id.id_wrong_practice_text_choice_b);
        mTextChoiceC= (TextView) findViewById(R.id.id_wrong_practice_text_choice_c);
        mTextChoiceD= (TextView) findViewById(R.id.id_wrong_practice_text_choice_d);
        mButtonNext = (Button) findViewById(R.id.id_wrong_practice_button_next);
        mButtonDelete = (ImageButton) findViewById(R.id.id_question_title_button_delete);

        mButtonBack.setOnClickListener(this);
        mLayoutDetailExplain.setOnClickListener(this);
        mButtonNext.setOnClickListener(this);
        mLayoutChoiceA.setOnClickListener(this);
        mLayoutChoiceB.setOnClickListener(this);
        mLayoutChoiceC.setOnClickListener(this);
        mLayoutChoiceD.setOnClickListener(this);
        mButtonExplain.setOnClickListener(this);
        mButtonCollect.setOnClickListener(this);
        mButtonExplain.setOnClickListener(this);
        mButtonDelete.setOnClickListener(this);

        mTextNum.setText(mCurrentIndex+1 + "/" + mQuestionsCount);
        mTextTitle.setText(mCurrentQuestionItem.getQuestion());
        mTextChoiceA.setText(mCurrentQuestionItem.getItem1());
        mTextChoiceB.setText(mCurrentQuestionItem.getItem2());
        mTextChoiceC.setText(mCurrentQuestionItem.getItem3());
        mTextChoiceD.setText(mCurrentQuestionItem.getItem4());
        mTextExplain.setText(mCurrentQuestionItem.getExplains());
        mLayoutDelete.setVisibility(View.VISIBLE);
        mLayoutExclude.setVisibility(View.GONE);


        if(!TextUtils.isEmpty(mCurrentQuestionItem.getUrl())){
            mImageQuestion.setVisibility(View.VISIBLE);
            mImageLoader.showImage(mCurrentQuestionItem.getUrl(),mImageQuestion);
        }

        updateCollectUI();
    }

    @Override
    public void initData() {
        initQuestionData();
        mCurrentQuestionItem = mQuestions.get(0);
    }

    private void initQuestionData() {
        Cursor cursor = mSQLiteManager.getAllWrongQuestions();
        mQuestions = new ArrayList<>();
        while(cursor.moveToNext()){
            QuestionItem item = EntityConvertManager.getQuestionItemEntity(cursor);
            mQuestions.add(item);
        }
        mQuestionsCount =mQuestions.size();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_question_title_button_back:
                finishActivity(this);
                break;

            case R.id.id_question_title_button_explain:
                showExplain();
                break;

            case R.id.id_wrong_practice_button_next:
                showNextQuestion();
                break;

            case R.id.id_wrong_practice_layout_choice_a:
                handleAnswerAction(ANSWER_A,mImageChoiceA);
                break;

            case R.id.id_wrong_practice_layout_choice_b:
                handleAnswerAction(ANSWER_B,mImageChoiceB);
                break;

            case R.id.id_wrong_practice_layout_choice_c:
                handleAnswerAction(ANSWER_C,mImageChoiceC);
                break;

            case R.id.id_wrong_practice_layout_choice_d:
                handleAnswerAction(ANSWER_D,mImageChoiceD);
                break;
            case R.id.id_question_title_button_collect:
                handleCollectAction();
                break;
            case R.id.id_question_title_button_delete:
                handleDeleteWrongQuestion();
                break;
        }
    }

    //删除错题
    private void handleDeleteWrongQuestion() {
        mSQLiteManager.deleteItemFromWrongQuestionById(mCurrentQuestionItem.getId());
        showNextQuestion();
        ToastManager.showShortMsg("删除成功!");

    }

    //排除一个错误答案

    private void handleCollectAction() {
        if(checkCollected(mCurrentQuestionItem.getId())){
            ToastManager.showAlreadyCollectMsg();
            return ;
        }else{
            ToastManager.showCollectSuccessMsg();
            mButtonCollect.setBackgroundResource(R.mipmap.icon_examin_selected_shoucang);
            saveCollectQuestion(mCurrentQuestionItem);
        }

    }

    //检查答案
    private boolean checkAnswer(String answer) {
        if(mCurrentQuestionItem.getAnswer().equals(answer)){
            return true ;
        }
        return false;
    }

    private void handleAnswerAction(String answer,ImageView imageView){
        mIsChoiceOneAnswer = true;
        setAllAnswerUnSelect();
        if(checkAnswer(answer)){
            //选中正确答案
            showRightAnswerImage(imageView);
        }else{
            //选中错误答案
            showWrongAnswerImage(imageView);
            //禁止再次选择
            setAllAnswerUnSelect();
            //显示正确答案
            showRightAnswer(mCurrentQuestionItem.getAnswer());
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
            //没有进行选择
//            if(!mIsChoiceOneAnswer){
//                ToastManager.showSelectOneAnswerMsg();
//                return;
//            }
            if(++mCurrentIndex== mQuestions.size()){
                mCurrentIndex--;
                //清空数据库，重新请求随机数据插入数据类
                ToastManager.showCompleteWrongQuestionMsg();
                return;
            }
            initUI();
            mCurrentQuestionItem = mQuestions.get(mCurrentIndex);
            updateUI(mCurrentQuestionItem);
            mIsChoiceOneAnswer = false;
            mIsExcluded = false;
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

    //显示解释
    private void showExplain() {
        mLayoutDetailExplain.setVisibility(View.VISIBLE);
    }

    private void hideExplain(){
        mLayoutDetailExplain.setVisibility(View.GONE);
    }

    private void initUI(){
        hideExplain();
        mButtonCollect.setBackgroundResource(R.mipmap.icon_examin_shoucang);
        mImageChoiceA.setImageResource(R.mipmap.choice_a);
        mImageChoiceB.setImageResource(R.mipmap.choice_b);
        mImageChoiceC.setImageResource(R.mipmap.choice_c);
        mImageChoiceD.setImageResource(R.mipmap.choice_d);
        setAllAnswerSelect();
    }

    private void updateUI(QuestionItem item){
        mTextNum.setText(mCurrentIndex+1 + "/" + mQuestionsCount);
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
        }else{
            mImageQuestion.setVisibility(View.GONE);
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

        updateCollectUI();

    }

    private void updateCollectUI(){
        if(checkCollected(mCurrentQuestionItem.getId())){
            setCollectImageSelected(mButtonCollect);
        }
    }
}

