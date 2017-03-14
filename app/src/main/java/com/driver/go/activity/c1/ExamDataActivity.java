package com.driver.go.activity.c1;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.driver.go.R;
import com.driver.go.activity.DriverBaseActivity;
import com.driver.go.adapter.CommonAdapter;
import com.driver.go.adapter.ViewHolder;
import com.driver.go.control.EntityConvertManager;
import com.driver.go.entity.ExamRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by malijie on 2017/3/13.
 */

public class ExamDataActivity extends DriverBaseActivity implements View.OnClickListener{
    private ImageButton mButtonBack;
    private ListView mListView;


    List<ExamRecord> mExamRecords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_data_layout);
        initData();
        initView();
    }

    @Override
    public void initView() {

        mListView = (ListView) findViewById(R.id.id_exam_data_lv);
        mButtonBack = (ImageButton) findViewById(R.id.id_title_bar_button_back);
        mButtonBack.setOnClickListener(this);
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
        Cursor cursor = mSQLiteManager.getExamRecord();
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
