package com.driver.go.activity.c1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.driver.go.R;
import com.driver.go.activity.DriverBaseActivity;
import com.driver.go.base.Profile;
import com.driver.go.control.IntentManager;
import com.driver.go.db.DBConstants;
import com.driver.go.entity.QuestionItem;
import com.driver.go.http.RetrofitHttpRequest;
import com.driver.go.http.SubscriberOnNextListener;
import com.driver.go.utils.ToastManager;
import com.driver.go.utils.Util;
import com.driver.go.widget.dialog.CustomDialog;

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
    private int mCurrentIndex = 0;
    private List<QuestionItem> mQuestions = null;
    private boolean mIsChoiceOneAnswer = false;
    private QuestionItem mCurrentQuestionItem;

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
    private Button mButtonNext;


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
        mButtonNext = (Button) findViewById(R.id.id_exam_main_button_next);

        mButtonBack.setOnClickListener(this);
        mButtonCollect.setOnClickListener(this);
        mButtonNext.setOnClickListener(this);
        mLayoutChoiceA.setOnClickListener(this);
        mLayoutChoiceB.setOnClickListener(this);
        mLayoutChoiceC.setOnClickListener(this);
        mLayoutChoiceD.setOnClickListener(this);

    }

    @Override
    public void initData() {
        mRetrofitRequest = RetrofitHttpRequest.getInstance();
        initTime();
        initQuestion();
        clearTableData(DBConstants.EXAM_WRONG_QUESTION_TABLE);
    }

    private void initQuestion() {
        mRetrofitRequest.getC1Subject1RandomQuestions(new SubscriberOnNextListener<List<QuestionItem>>(){
            @Override
            public void onNext(List<QuestionItem> questionItems) {
                mQuestions = questionItems;
                mCurrentQuestionItem = mQuestions.get(0);
                updateUI(mCurrentQuestionItem);
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
                        saveExamRecordData();
                        showTimeUpDialog();
                        return;
                    }

                    if(mTimeSecond <10){
                        mStrSecond = "0" + mTimeSecond;
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

    private void saveExamRecordData() {
        mSQLiteManager.insertExamRecordData(DBConstants.C1_EXAM_RECORD_TABLE,Util.getCurrentDate(),getExamScore());
    }

    private void showTimeUpDialog() {
        String title = Util.getResString(R.string.dialog_title_time_up) + getExamScore() + Util.getResString(R.string.dialog_title_check_wrong_questions);
        final CustomDialog dialog = new CustomDialog(this,title);

        dialog.setButtonClickListener(new CustomDialog.DialogButtonListener() {
            @Override
            public void onConfirm() {
                IntentManager.finishActivity(ExamMainActivity.this);
            }

            @Override
            public void onCancel() {
                dialog.dissmiss();
            }
        });

        dialog.show();
    }

    private int getExamScore(){
        return 100-mSQLiteManager.getExamWrongQuestionCount();
    }

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
                handleCollectAction();
                break;

            case R.id.id_exam_main_button_next:
                showNextQuestion();
                break;

            case R.id.id_exam_main_layout_choice_a:
                handleAnswerAction(ANSWER_A,mImageChoiceA);
                break;

            case R.id.id_exam_main_layout_choice_b:
                handleAnswerAction(ANSWER_B,mImageChoiceB);

                break;

            case R.id.id_exam_main_layout_choice_c:
                handleAnswerAction(ANSWER_C,mImageChoiceC);

                break;
            case R.id.id_exam_main_layout_choice_d:
                handleAnswerAction(ANSWER_D,mImageChoiceD);

                break;
        }
    }

    /**
     * 点击收藏
     */
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

    private void handleAnswerAction(String answer,ImageView imageView){
        mIsChoiceOneAnswer = true;
        setAllAnswerUnSelect();
        if(checkAnswer(answer)){
            //选中正确答案
            showRightAnswerImage(imageView);
        }else{
            //选中错误答案
            showWrongAnswerImage(imageView);
            //记录错题
            addExamWrongQuestionItem(mCurrentQuestionItem);
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

    //检查答案
    private boolean checkAnswer(String answer) {
        if(mCurrentQuestionItem.getAnswer().equals(answer)){
            return true ;
        }
        return false;
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

    //下一题
    private void showNextQuestion() {
        if(hasInternet()){
            //没有进行选择
            if(!mIsChoiceOneAnswer){
                ToastManager.showSelectOneAnswerMsg();
                return;
            }

            if(++mCurrentIndex >= Profile.EXAM_TOTAL_ITEM){
                mCurrentIndex--;
                saveExamRecordData();
                showCompleteExamDialog();
                return;
            }

            initUI();
            mCurrentQuestionItem = mQuestions.get(mCurrentIndex);
            updateUI(mCurrentQuestionItem);
            mIsChoiceOneAnswer = false;
        }else{
            ToastManager.showLongMsg(getString(R.string.current_network_unavailable));
        }

    }

    private void showCompleteExamDialog(){
        String title = "模拟考试得分" + getExamScore()  + ",是否查看错题";
        CustomDialog dialog = new CustomDialog(this,title);
        dialog.setButtonClickListener(new CustomDialog.DialogButtonListener() {
            @Override
            public void onConfirm() {
                IntentManager.startActivity(ExamWrongQuestionActivity.class);
                IntentManager.finishActivity(ExamMainActivity.this);
            }

            @Override
            public void onCancel() {
                IntentManager.finishActivity(ExamMainActivity.this);
            }
        });
        dialog.show();


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
        mTitleCount.setText(mCurrentIndex+1 + "/" + sExamQuestionTotalNum);
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

    @Override
    public void onBackPressed() {
        final CustomDialog dialog = new CustomDialog(this,
                Util.getResString(R.string.dialog_title_exam_exit));

        dialog.setButtonClickListener(new CustomDialog.DialogButtonListener() {
            @Override
            public void onConfirm() {
                IntentManager.finishActivity(ExamMainActivity.this);
            }

            @Override
            public void onCancel() {
                dialog.dissmiss();
            }
        });

        dialog.show();
    }
}
