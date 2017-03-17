package com.driver.go.activity.subject1;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.driver.go.R;
import com.driver.go.activity.base.SubjectOneBaseActivity;
import com.driver.go.adapter.CommonAdapter;
import com.driver.go.adapter.ViewHolder;
import com.driver.go.control.EntityConvertManager;
import com.driver.go.entity.ExamRecord;
import com.driver.go.utils.SharePreferenceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malijie on 2017/3/13.
 */

public class ExamDataActivity extends SubjectOneBaseActivity implements View.OnClickListener{
    private ImageButton mButtonBack;
    private ListView mListView;
    private TextView mTextNoQuestionsCount;
    private TextView mTextRightQuestionsCount;
    private TextView mTextWrongQuestionsCount;
    private List<ExamRecord> mExamRecords;
    private TextView mTextMaxScore;

    private int maxScore;
    private int wrongQuestionCount;
    private int noWrittenQuestionCount;
    private int rightQuestionCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_data_layout);
        initData();
        initView();
    }

    @Override
    public void initView() {
        mTextMaxScore = (TextView) findViewById(R.id.id_exam_data_text_max_score);
        mListView = (ListView) findViewById(R.id.id_exam_data_lv);
        mButtonBack = (ImageButton) findViewById(R.id.id_title_bar_button_back);
        mTextNoQuestionsCount  = (TextView) findViewById(R.id.id_exam_data_text_no_questions);
        mTextRightQuestionsCount = (TextView) findViewById(R.id.id_exam_data_text_right_questions);
        mTextWrongQuestionsCount = (TextView) findViewById(R.id.id_exam_data_text_wrong_questions);

        mButtonBack.setOnClickListener(this);

        mTextMaxScore.setText(String.valueOf(maxScore));
        mTextNoQuestionsCount.setText(String.valueOf(noWrittenQuestionCount));
        mTextRightQuestionsCount.setText(String.valueOf(rightQuestionCount));
        mTextWrongQuestionsCount.setText(String.valueOf(wrongQuestionCount));

        mListView.setAdapter(new CommonAdapter<ExamRecord>(this,R.layout.exam_data_item,mExamRecords) {
            @Override
            public void convert(ViewHolder holder, ExamRecord item) {
                holder.setText(R.id.id_exam_data_item_text_time_value,item.getDate());
                holder.setText(R.id.id_exam_data_item_text_score_value,item.getScore()+"");
            }
        });
    }

    @Override
    public void initData() {
        Cursor cursor = mSQLiteManager.getExamRecordResult();
        maxScore = mSQLiteManager.getMaxScore();
        wrongQuestionCount = mSQLiteManager.getExamWrongQuestionCount();
        noWrittenQuestionCount = sOrderQuestionTotalNum - SharePreferenceUtil.loadSubject1OrderQuestionIndex();
        rightQuestionCount = sOrderQuestionTotalNum - wrongQuestionCount;

        mExamRecords = new ArrayList<>();
        for(cursor.moveToFirst();cursor.moveToNext();){
            ExamRecord record = EntityConvertManager.getExamRecordEntity(cursor);
            mExamRecords.add(record);
        }
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
