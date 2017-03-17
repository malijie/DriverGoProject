package com.driver.go.activity.base;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageButton;

import com.driver.go.R;
import com.driver.go.base.Profile;
import com.driver.go.control.IntentManager;
import com.driver.go.db.DBConstants;
import com.driver.go.db.SQLiteManager;
import com.driver.go.entity.QuestionItem;
import com.driver.go.http.RetrofitHttpRequest;
import com.driver.go.utils.Logger;
import com.driver.go.utils.SharePreferenceUtil;
import com.driver.go.utils.Util;
import com.driver.go.utils.image.ImageLoader;

/**
 * Created by Administrator on 2016/11/5.
 */
public abstract class DriverBaseActivity extends FragmentActivity {
    public static final int SUBJECT_TYPE_1 = 1;
    public static final int SUBJECT_TYPE_4 = 4;
    public static int sOrderQuestionTotalNum = Profile.ORDER_TOTAL_ITEM;
    public static int sRandomQuestionTotalNum = Profile.RANDOM_TOTAL_ITEM;
    public static final int sExamQuestionTotalNum = Profile.EXAM_TOTAL_ITEM;
    public RetrofitHttpRequest mRetrofitHttpRequest = null;
    public SQLiteManager mSQLiteManager = null;
    public ImageLoader mImageLoader = null;
    protected final String ANSWER_A = "1";
    protected final String ANSWER_B = "2";
    protected final String ANSWER_C = "3";
    protected final String ANSWER_D = "4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDB();
        initManager();
    }

    private void initManager() {
        mRetrofitHttpRequest = RetrofitHttpRequest.getInstance();
        mImageLoader = ImageLoader.getInstance();
    }


    private void initDB(){
        mSQLiteManager = SQLiteManager.getInstance();
        mSQLiteManager.createTables();
    }

    public boolean isOrderTableExist(){
        return mSQLiteManager.isOrderTableHasData();
    }

    public void addSubject1OrderQuestionItem(QuestionItem q){
        mSQLiteManager.insertQuestion2Table(DBConstants.SUBJECT1_ORDER_EXAM_TABLE,q.getId(),q.getQuestion(),q.getAnswer(),q.getItem1(),q.getItem2(),q.getItem3(),q.getItem4(),q.getExplains(),q.getUrl());
    }

    public void addSubject4OrderQuestionItem(QuestionItem q){
        mSQLiteManager.insertQuestion2Table(DBConstants.SUBJECT4_ORDER_EXAM_TABLE,q.getId(),q.getQuestion(),q.getAnswer(),q.getItem1(),q.getItem2(),q.getItem3(),q.getItem4(),q.getExplains(),q.getUrl());
    }

    public void addWrongQuestionItem(QuestionItem q){
        mSQLiteManager.insertQuestion2Table(DBConstants.SUBJECT1_PRACTISE_WRONG_QUESTION_TABLE,q.getId(),q.getQuestion(),q.getAnswer(),q.getItem1(),q.getItem2(),q.getItem3(),q.getItem4(),q.getExplains(),q.getUrl());
    }

//    protected void saveCollectQuestion(QuestionItem q){
//        mSQLiteManager.insertQuestion2Table(DBConstants.SUBJECT1_COLLECT_QUESTION_TABLE,q.getId(),q.getQuestion(),q.getAnswer(),q.getItem1(),q.getItem2(),q.getItem3(),q.getItem4(),q.getExplains(),q.getUrl());
//    }

    public void addExamWrongQuestionItem(QuestionItem q){
        mSQLiteManager.insertQuestion2Table(DBConstants.SUBJECT1_EXAM_WRONG_QUESTION_TABLE,q.getId(),q.getQuestion(),q.getAnswer(),q.getItem1(),q.getItem2(),q.getItem3(),q.getItem4(),q.getExplains(),q.getUrl());
    }

    public void finishActivity(Activity activity){
        IntentManager.finishActivity(activity);
    }

    public boolean hasInternet(){
        return Util.hasInternet();
    }

    protected int loadOrderQuestionIndex(int subjectType){
        if(subjectType == SUBJECT_TYPE_1){
            return SharePreferenceUtil.loadSubject1OrderQuestionIndex();
        }
        return SharePreferenceUtil.loadSubject4OrderQuestionIndex();
    }

    protected void saveOrderQuestionIndex(int subjectType,int index){
        if(subjectType == SUBJECT_TYPE_1){
            SharePreferenceUtil.saveSubject1OrderQuestionIndex(index);
        }else if(subjectType == SUBJECT_TYPE_4){
            SharePreferenceUtil.saveSubject4OrderQuestionIndex(index);
        }
    }

    protected void saveReciteQuestionIndex(int subjectType,int index){
        if(subjectType == SUBJECT_TYPE_1){
            SharePreferenceUtil.saveSubject1ReciteQuestionIndex(index);
        }else if(subjectType == SUBJECT_TYPE_4){
            SharePreferenceUtil.saveSubject4ReciteQuestionIndex(index);
        }
    }

    protected int loadReciteQuestionIndex(int subjectType){
        if(subjectType == SUBJECT_TYPE_1){
            SharePreferenceUtil.loadSubject1ReciteQuestionIndex();
        }
        return SharePreferenceUtil.loadSubject4ReciteQuestionIndex();
    }

    protected int getQuestionIdByIndex(int index){
        Cursor cursor = mSQLiteManager.queryRandomQuestionByIndex(index);
        return cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(1)));
    }

    protected void setCollectImageSelected(ImageButton imageButton){
        imageButton.setBackgroundResource(R.mipmap.icon_examin_selected_shoucang);
    }


    protected void clearTableData(String tableName){
        mSQLiteManager.clearTableData(tableName);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSQLiteManager.closeDB();
    }
}
