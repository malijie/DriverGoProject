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
import com.driver.go.control.EntityConvertManager;
import com.driver.go.entity.QuestionItem;
import com.driver.go.utils.Logger;
import com.driver.go.utils.image.ImageLoader;


/**
 * Created by Administrator on 2016/12/1.
 */
public class PracticeOrderActivity extends DriverBaseActivity implements View.OnClickListener{

    private ImageButton mButtonBack;
    private TextView mTextNum;
    private LinearLayout mLayoutExculde;
    private LinearLayout mLayoutExplaint;
    private ImageView mImageItem;
    private ImageView mImageQuestion;
    private TextView mTextTitle;
    private TextView mTextChoiceA;
    private TextView mTextChoiceB;
    private TextView mTextChoiceC;
    private TextView mTextChoiceD;
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

        mTextNum.setText(mCurrentId + "/" + sOrderQuestionTotalNum);
        mTextTitle.setText(mCurrentQuestionItem.getQuestion());
        mTextChoiceA.setText(mCurrentQuestionItem.getItem1());
        mTextChoiceB.setText(mCurrentQuestionItem.getItem2());
        mTextChoiceC.setText(mCurrentQuestionItem.getItem3());
        mTextChoiceD.setText(mCurrentQuestionItem.getItem4());
        Logger.d("url=" +mCurrentQuestionItem.getUrl() );

        if(!mCurrentQuestionItem.getUrl().equals("")){
            mImageQuestion.setVisibility(View.VISIBLE);
            ImageLoader.showImage(mCurrentQuestionItem.getUrl(),mImageQuestion);
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

                break;
            case R.id.id_order_practice_button_previous:

                break;
            case R.id.id_order_practice_button_next:

                break;
        }
    }
}
