package com.driver.go.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.driver.go.R;
import com.driver.go.activity.CollectQuestionsActivity;
import com.driver.go.activity.DriverExamSkillActivity;
import com.driver.go.activity.DriverTipActivity;
import com.driver.go.activity.ExamMainActivity;
import com.driver.go.activity.PracticeOrderActivity;
import com.driver.go.activity.PracticeRandomActivity;
import com.driver.go.activity.PractiseWrongQuestionActivity;
import com.driver.go.activity.RecitePracticeOrderActivity;
import com.driver.go.control.IntentManager;
import com.driver.go.db.SQLiteManager;
import com.driver.go.utils.ToastManager;

//科目一
public class SubjectOneFragment extends Fragment implements View.OnClickListener{
    private SQLiteManager mSQLiteManager = null;
    private ImageButton mButtonOrderPractise;
    private ImageButton mButtonReciteQuestion;
    private ImageButton mButtonRandomQuestion;
    private ImageButton mButtonWrongQuestion;
    private ImageButton mButtonCollectQuestion;
    private ImageButton mButtonDriverTip;
    private ImageButton mButtonSkill;
    private ImageButton mButtonExam;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {   View v = inflater.inflate(R.layout.subject_one_fragment, container, false);
        initViews(v);
        initData();
        return v;
    }

    private void initData() {
        mSQLiteManager = SQLiteManager.getInstance();
    }

    private void initViews(View v) {
        mButtonOrderPractise = (ImageButton) v.findViewById(R.id.id_main_image_order_practice);
        mButtonReciteQuestion = (ImageButton) v.findViewById(R.id.id_main_image_recite_question);
        mButtonRandomQuestion = (ImageButton) v.findViewById(R.id.id_main_image_random_question);
        mButtonWrongQuestion = (ImageButton) v.findViewById(R.id.id_main_button_wrong_question);
        mButtonCollectQuestion = (ImageButton) v.findViewById(R.id.id_main_button_collect_question);
        mButtonDriverTip = (ImageButton) v.findViewById(R.id.id_main_button_driver_tip);
        mButtonExam = (ImageButton) v.findViewById(R.id.id_main_button_exam);
        mButtonSkill = (ImageButton) v.findViewById(R.id.id_main_button_driver_skill);
        mButtonSkill.setOnClickListener(this);
        mButtonExam.setOnClickListener(this);
        mButtonDriverTip.setOnClickListener(this);
        mButtonRandomQuestion.setOnClickListener(this);
        mButtonOrderPractise.setOnClickListener(this);
        mButtonReciteQuestion.setOnClickListener(this);
        mButtonWrongQuestion.setOnClickListener(this);
        mButtonCollectQuestion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_main_image_order_practice:
                IntentManager.startActivity(PracticeOrderActivity.class);
                break;
            case R.id.id_main_image_recite_question:
                IntentManager.startActivity(RecitePracticeOrderActivity.class);
                break;
            case R.id.id_main_image_random_question:
                IntentManager.startActivity(PracticeRandomActivity.class);
                break;
            case R.id.id_main_button_wrong_question:
                if(checkHasWrongQuestions()){
                    IntentManager.startActivity(PractiseWrongQuestionActivity.class);
                }else {
                    ToastManager.showNoWrongQuestionMsg();
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
        }
    }

    private boolean checkHasWrongQuestions(){
       return mSQLiteManager.hasWrongQuestions();
    }

    private boolean checkHasCollectQuestions(){
        return mSQLiteManager.hasCollectQuestions();
    }
}
