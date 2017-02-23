package com.driver.go.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.driver.go.R;
import com.driver.go.control.IntentManager;
import com.driver.go.entity.QuestionItem;
import com.driver.go.utils.Logger;

import static com.driver.go.control.IntentManager.finshActivity;


/**
 * Created by Administrator on 2016/12/1.
 */
public class PracticeOrderActivity extends DriverBaseActivity implements View.OnClickListener{

    private ImageButton mButtonBack;
    private TextView mTextNum;
    private LinearLayout mLayoutExculde;
    private LinearLayout mLayoutExplaint;
    private ImageView mImageItem;
    private TextView mTextTitle;
    private TextView mTextChoiceA;
    private TextView mTextChoiceB;
    private TextView mTextChoiceC;
    private TextView mTextChoiceD;
    private Button mButtonPre;
    private Button mButtonNext;

    private int mCurrentId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_practise);
        initView();
        initData();
    }

    @Override
    public void initView() {
        mButtonBack = (ImageButton) findViewById(R.id.id_question_title_button_back);
        mTextNum = (TextView) findViewById(R.id.id_question_title_text_num);
        mLayoutExculde = (LinearLayout) findViewById(R.id.id_question_title_layout_exclude);
        mLayoutExplaint = (LinearLayout) findViewById(R.id.id_question_title_layout_explain);
        mImageItem = (ImageView) findViewById(R.id.id_order_practice_image_item);
        mTextTitle = (TextView) findViewById(R.id.id_order_practice_text_title);
        mTextChoiceA= (TextView) findViewById(R.id.id_order_practice_text_choice_a);
        mTextChoiceB= (TextView) findViewById(R.id.id_order_practice_text_choice_b);
        mTextChoiceC= (TextView) findViewById(R.id.id_order_practice_text_choice_c);
        mTextChoiceD= (TextView) findViewById(R.id.id_order_practice_text_choice_d);
        mButtonPre = (Button) findViewById(R.id.id_order_practice_button_previous);
        mButtonNext = (Button) findViewById(R.id.id_order_practice_button_next);
        mButtonBack.setOnClickListener(this);
    }

    @Override
    public void initData() {
        Cursor cursor = mSQLiteManager.queryOrderQuestionById(1);
        QuestionItem questionItem = new QuestionItem();
        questionItem.setQuestion(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
//        questionItem.setAnswer(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));

        Logger.d("MLJ","questionItem=" + questionItem);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_question_title_button_back:
                finshActivity(this);
                break;
            case R.id.id_question_title_layout_exclude:

                break;
            case R.id.id_question_title_layout_explain:

                break;
            case R.id.id_order_practice_button_previous:

                break;
            case R.id.id_order_practice_button_next:

                break;
        }
    }
}
