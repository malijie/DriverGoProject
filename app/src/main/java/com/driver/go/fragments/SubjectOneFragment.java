package com.driver.go.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.driver.go.R;
import com.driver.go.activity.subject1.CollectQuestionsActivity;
import com.driver.go.activity.subject1.DriverExamSkillActivity;
import com.driver.go.activity.subject1.DriverTipActivity;
import com.driver.go.activity.subject1.ExamDataActivity;
import com.driver.go.activity.subject1.ExamMainActivity;
import com.driver.go.activity.subject1.ExamWrongQuestionActivity;
import com.driver.go.activity.subject1.PracticeOrderActivity;
import com.driver.go.activity.subject1.PracticeRandomActivity;
import com.driver.go.activity.subject1.PractiseWrongQuestionActivity;
import com.driver.go.activity.subject1.RecitePracticeOrderActivity;
import com.driver.go.control.IntentManager;
import com.driver.go.db.DBConstants;
import com.driver.go.db.SubjectOneSQLiteBehavior;
import com.driver.go.utils.ToastManager;


//科目一
public class SubjectOneFragment extends BaseFragment implements View.OnClickListener{
    private ImageButton mButtonExamData;
    private ImageButton mButtonPractiseWrongQuestion;
    private ImageButton mButtonOrderPractise;
    private ImageButton mButtonReciteQuestion;
    private ImageButton mButtonRandomQuestion;
    private ImageButton mButtonExamWrongQuestion;
    private ImageButton mButtonCollectQuestion;
    private ImageButton mButtonDriverTip;
    private ImageButton mButtonSkill;
    private ImageButton mButtonExam;
    private ImageButton mButtonFeedback;
    private ImageButton mButtonUpdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreateView(inflater,container,savedInstanceState);
        View v = inflater.inflate(R.layout.subject_one_fragment, container, false);
        initViews(v);
        initData();
        return v;
    }

    public void initData() {
        mSQLiteManager.setSubjectBehavior(SubjectOneSQLiteBehavior.getInstance());
    }

    private void initViews(View v) {
        mButtonOrderPractise = (ImageButton) v.findViewById(R.id.id_main_image_order_practice);
        mButtonReciteQuestion = (ImageButton) v.findViewById(R.id.id_main_image_recite_question);
        mButtonRandomQuestion = (ImageButton) v.findViewById(R.id.id_main_image_random_question);
        mButtonExamWrongQuestion = (ImageButton) v.findViewById(R.id.id_main_button_exam_wrong_question);
        mButtonCollectQuestion = (ImageButton) v.findViewById(R.id.id_main_button_collect_question);
        mButtonDriverTip = (ImageButton) v.findViewById(R.id.id_main_button_driver_tip);
        mButtonExam = (ImageButton) v.findViewById(R.id.id_main_button_exam);
        mButtonSkill = (ImageButton) v.findViewById(R.id.id_main_button_driver_skill);
        mButtonPractiseWrongQuestion = (ImageButton) v.findViewById(R.id.id_main_button_practise_wrong_question);
        mButtonExamData = (ImageButton) v.findViewById(R.id.id_main_button_exam_data);
        mButtonFeedback = (ImageButton) v.findViewById(R.id.id_bottom_button_feedback);
        mButtonUpdate = (ImageButton) v.findViewById(R.id.id_bottom_button_update);

        mButtonSkill.setOnClickListener(this);
        mButtonExamData.setOnClickListener(this);
        mButtonExam.setOnClickListener(this);
        mButtonDriverTip.setOnClickListener(this);
        mButtonRandomQuestion.setOnClickListener(this);
        mButtonOrderPractise.setOnClickListener(this);
        mButtonReciteQuestion.setOnClickListener(this);
        mButtonExamWrongQuestion.setOnClickListener(this);
        mButtonCollectQuestion.setOnClickListener(this);
        mButtonPractiseWrongQuestion.setOnClickListener(this);
        mButtonFeedback.setOnClickListener(this);
        mButtonUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_main_image_order_practice:
                //顺序练习
                if(isDownloadSubject1DB()){
                    IntentManager.startActivity(PracticeOrderActivity.class);
                }else{
                    ToastManager.showNoNetworkMsg();
                }
                break;
            case R.id.id_main_image_recite_question:
                if(isDownloadSubject1DB()){
                    IntentManager.startActivity(RecitePracticeOrderActivity.class);
                }else{
                    ToastManager.showNoNetworkMsg();
                }
                break;
            case R.id.id_main_image_random_question:
                IntentManager.startActivity(PracticeRandomActivity.class);
                break;
            case R.id.id_main_button_exam_wrong_question:
                if(checkHasExamWrongQuestions()){
                    IntentManager.startActivity(ExamWrongQuestionActivity.class);
                }else {
                    ToastManager.showNoExamWrongQuestionMsg();
                }
                break;
            case R.id.id_main_button_collect_question:
                if(checkHasCollectQuestions()){
                    IntentManager.startActivity(CollectQuestionsActivity.class);
                }else {
                    ToastManager.showNoCollectQuestionMsg();
                }
                break;
            case R.id.id_main_button_driver_tip:
                IntentManager.startActivity(DriverTipActivity.class);
                break;
            case R.id.id_main_button_exam:
                IntentManager.startActivity(ExamMainActivity.class);
                break;
            case R.id.id_main_button_driver_skill:
                IntentManager.startActivity(DriverExamSkillActivity.class);
                break;
            case R.id.id_main_button_practise_wrong_question:
                if(checkHasPractiseWrongQuestions()){
                    IntentManager.startActivity(PractiseWrongQuestionActivity.class);
                }else {
                    ToastManager.showNoWrongQuestionMsg();
                }
                break;
            case R.id.id_main_button_exam_data:
               //考试数据
                IntentManager.startActivity(ExamDataActivity.class);
                break;
            case R.id.id_bottom_button_feedback:
                //应用反馈
                mWapManager.feedbackApp();
                break;
            case R.id.id_bottom_button_update:
                //应用升级
                mWapManager.updateApp();
                break;
        }
    }

    private boolean checkHasPractiseWrongQuestions(){
       return mSQLiteManager.hasQuestions(DBConstants.SUBJECT1_PRACTISE_WRONG_QUESTION_TABLE);
    }

    private boolean checkHasExamWrongQuestions(){
        return mSQLiteManager.hasQuestions(DBConstants.SUBJECT1_EXAM_WRONG_QUESTION_TABLE);
    }

    private boolean checkHasCollectQuestions(){
        return mSQLiteManager.hasCollectQuestions(DBConstants.SUBJECT1_COLLECT_QUESTION_TABLE);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mWapManager.close();
    }
}
