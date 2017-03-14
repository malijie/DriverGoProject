package com.driver.go.activity.c1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.driver.go.R;
import com.driver.go.activity.DriverBaseActivity;
import com.driver.go.adapter.CommonAdapter;
import com.driver.go.adapter.ViewHolder;
import com.driver.go.entity.ExamRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by malijie on 2017/3/13.
 */

public class ExamDataActivity extends DriverBaseActivity{
    private ListView mListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_data_layout);
        initView();
        initData();
    }

    @Override
    public void initView() {
        List<ExamRecord> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            ExamRecord record = new ExamRecord();
            record.setDate(new Date().toString());
            record.setScore(100);
            list.add(record);
        }
        mListView = (ListView) findViewById(R.id.id_exam_data_lv);
        mListView.setAdapter(new CommonAdapter<ExamRecord>(this,R.layout.exam_data_item,list) {
            @Override
            public void convert(ViewHolder holder, ExamRecord item) {
                holder.setText(R.id.id_exam_data_item_text_time_value,item.getDate());
                holder.setText(R.id.id_exam_data_item_text_score_value,item.getScore()+"");
            }
        });
    }

    @Override
    public void initData() {

    }
}
