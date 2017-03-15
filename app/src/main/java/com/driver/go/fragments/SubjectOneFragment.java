package com.driver.go.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.driver.go.R;
import com.driver.go.activity.c1.CollectQuestionsActivity;
import com.driver.go.activity.c1.DriverExamSkillActivity;
import com.driver.go.activity.c1.DriverTipActivity;
import com.driver.go.activity.c1.ExamDataActivity;
import com.driver.go.activity.c1.ExamMainActivity;
import com.driver.go.activity.c1.ExamWrongQuestionActivity;
import com.driver.go.activity.c1.PracticeOrderActivity;
import com.driver.go.activity.c1.PracticeRandomActivity;
import com.driver.go.activity.c1.PractiseWrongQuestionActivity;
import com.driver.go.activity.c1.RecitePracticeOrderActivity;
import com.driver.go.control.IntentManager;
import com.driver.go.db.DBConstants;
import com.driver.go.db.SQLiteManager;
import com.driver.go.entity.QuestionItem;
import com.driver.go.http.RetrofitHttpRequest;
import com.driver.go.http.SubscriberOnNextListener;
import com.driver.go.utils.Logger;
import com.driver.go.utils.ToastManager;
import com.driver.go.utils.Util;

import java.util.List;

//科目一
public class SubjectOneFragment extends BaseFragment implements View.OnClickListener{
    private SQLiteManager mSQLiteManager = null;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreateView(inflater,container,savedInstanceState);
        View v = inflater.inflate(R.layout.subject_one_fragment, container, false);
        initViews(v);
        initData();
        return v;
    }

    private void initData() {
        mSQLiteManager = SQLiteManager.getInstance();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        getActivity().registerReceiver(receiver,filter);
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_main_image_order_practice:
                //顺序练习
                if(isDownloadDB()){
                    IntentManager.startActivity(PracticeOrderActivity.class);
                }else{
                    ToastManager.showNoNetworkMsg();
                }
                break;
            case R.id.id_main_image_recite_question:
                if(isDownloadDB()){
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
        }
    }

    private boolean checkHasPractiseWrongQuestions(){
       return mSQLiteManager.hasQuestions(DBConstants.PRACTISE_WRONG_QUESTION_TABLE);
    }

    private boolean checkHasExamWrongQuestions(){
        return mSQLiteManager.hasQuestions(DBConstants.EXAM_WRONG_QUESTION_TABLE);
    }

    private boolean checkHasCollectQuestions(){
        return mSQLiteManager.hasCollectQuestions();
    }

    public BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                if (Util.hasInternet()) {
                    RetrofitHttpRequest.getInstance().getC1Subject1OrderQuestions(new SubscriberOnNextListener<List<QuestionItem>>(){
                        @Override
                        public void onNext(final List<QuestionItem> questionItems) {
                            new Thread( new Runnable() {
                                @Override
                                public void run() {
                                    for(QuestionItem item:questionItems){
                                        addOrderQuestionItem(item);
                                    }
                                }
                            }).start();

                        }
                    });
                }
            }
        }
    };

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(receiver);
        super.onDestroy();
    }
}
