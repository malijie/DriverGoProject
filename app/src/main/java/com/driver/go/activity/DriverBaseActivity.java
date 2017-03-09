package com.driver.go.activity;

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
import com.driver.go.utils.SharePreferenceUtil;
import com.driver.go.utils.Util;
import com.driver.go.utils.image.ImageLoader;

/**
 * Created by Administrator on 2016/11/5.
 */
public abstract class DriverBaseActivity extends FragmentActivity {
    public static int sOrderQuestionTotalNum = Profile.ORDER_TOTAL_ITEM;
    public static int sRandomQuestionTotalNum = Profile.RANDOM_TOTAL_ITEM;
    public static final int sExamQuestionTotalNum = Profile.EXAM_TOTAL_ITEM;
    public SQLiteManager mSQLiteManager = null;
    public ImageLoader mImageLoader = null;
    protected final String ANSWER_A = "1";
    protected final String ANSWER_B = "2";
    protected final String ANSWER_C = "3";
    protected final String ANSWER_D = "4";


    public abstract void initView();
    public abstract void initData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDB();
        initManager();
    }

    private void initManager() {
        mImageLoader = ImageLoader.getInstance();
    }


    private void initDB(){
        mSQLiteManager = SQLiteManager.getInstance();
        mSQLiteManager.createTables();
    }

    public boolean isOrderTableExist(){
        return mSQLiteManager.isOrderTableHasData();
    }

    public void addOrderQuestionItem(QuestionItem q){
        mSQLiteManager.insertQuestion2Table(DBConstants.ORDER_EXAM_TABLE,q.getId(),q.getQuestion(),q.getAnswer(),q.getItem1(),q.getItem2(),q.getItem3(),q.getItem4(),q.getExplains(),q.getUrl());
    }
    public void addRandomQuestionItem(QuestionItem q){
        mSQLiteManager.insertQuestion2Table(DBConstants.RANDOM_EXAM_TABLE,q.getId(),q.getQuestion(),q.getAnswer(),q.getItem1(),q.getItem2(),q.getItem3(),q.getItem4(),q.getExplains(),q.getUrl());
    }

    public void addWrongQuestionItem(QuestionItem q){
        mSQLiteManager.insertQuestion2Table(DBConstants.PRACTISE_WRONG_QUESTION_TABLE,q.getId(),q.getQuestion(),q.getAnswer(),q.getItem1(),q.getItem2(),q.getItem3(),q.getItem4(),q.getExplains(),q.getUrl());
    }

    protected void saveCollectQuestion(QuestionItem q){
        mSQLiteManager.insertQuestion2Table(DBConstants.COLLECT_QUESTION_TABLE,q.getId(),q.getQuestion(),q.getAnswer(),q.getItem1(),q.getItem2(),q.getItem3(),q.getItem4(),q.getExplains(),q.getUrl());
    }

    public void addExamWrongQuestionItem(QuestionItem q){
        mSQLiteManager.insertQuestion2Table(DBConstants.EXAM_WRONG_QUESTION_TABLE,q.getId(),q.getQuestion(),q.getAnswer(),q.getItem1(),q.getItem2(),q.getItem3(),q.getItem4(),q.getExplains(),q.getUrl());
    }

    public void finishActivity(Activity activity){
        IntentManager.finishActivity(activity);
    }

    public boolean hasInternet(){
        return Util.hasInternet();
    }

    protected void saveOrderQuestionIndex(int index){
        SharePreferenceUtil.saveOrderQuestionIndex(index);
    }

    protected int loadRandomQuestionIndex(){
       return SharePreferenceUtil.loadRandomQuestionIndex();
    }

    protected void saveRandomQuestionIndex(int index){
        SharePreferenceUtil.saveRandomQuestionIndex(index);
    }

    protected int loadOrderQuestionIndex(){
        return SharePreferenceUtil.loadOrderQuestionIndex();
    }

    protected void saveReciteQuestionIndex(int index){
        SharePreferenceUtil.saveReciteQuestionIndex(index);
    }

    protected int loadReciteQuestionIndex(){
        return SharePreferenceUtil.loadReciteQuestionIndex();
    }

    protected boolean checkCollected(int id){
        Cursor cursor = mSQLiteManager.queryCollectQuestionById(id);
        return cursor.moveToFirst();
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
