package com.driver.go.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.driver.go.entity.QuestionItem;
import com.driver.go.utils.ToastManager;

/**
 * Created by malijie on 2017/2/22.
 */

public class SQLiteManager extends SQLiteCommon{
    private Cursor cursor;
    private static SQLiteManager sSQLiteManager = null;
    private static SQLiteDatabase mDB;
    private ISQLiteBehavior mISQLiteBehavior;

    private SQLiteManager(SQLiteDatabase db){
        super(db);
    }

    public static SQLiteManager getInstance(){
        if(sSQLiteManager == null){
            synchronized (SQLiteManager.class){
                if(sSQLiteManager == null){
                    mDB = new SQLiteHelper().getWritableDatabase();
                    sSQLiteManager = new SQLiteManager(mDB);
                }
            }
        }
        return sSQLiteManager;
    }

    public void setSubjectBehavior(ISQLiteBehavior sqLiteBehavior){
        mISQLiteBehavior = sqLiteBehavior;
    }

    public Cursor queryOrderQuestionById(int id) {
        return mISQLiteBehavior.queryOrderQuestionById(id);
    }

    public Cursor queryCollectQuestionById(int id) {
        return mISQLiteBehavior.queryCollectQuestionById(id);
    }

    public Cursor queryRandomQuestionByIndex(int index) {
        return mISQLiteBehavior.queryRandomQuestionByIndex(index);
    }


    public Cursor getPractiseWrongQuestions(){
        return mISQLiteBehavior.getPractiseWrongQuestions();
    }

    public Cursor getAllExamWrongQuestions(){
       return mISQLiteBehavior.getAllExamWrongQuestions();
    }


    public Cursor getAllCollectQuestions() {
        return mISQLiteBehavior.getAllCollectQuestions();
    }

    public void deleteItemFromCollectQuestionById(int id) {
        mISQLiteBehavior.deleteItemFromCollectQuestionById(id);
    }

    public int getExamWrongQuestionCount() {
       return mISQLiteBehavior.getExamWrongQuestionCount();
    }

    public Cursor getExamRecordResult(){
        return mISQLiteBehavior.getExamRecordResult();
    }

    public int getMaxScore(){
        return mISQLiteBehavior.getMaxScore();
    }

    public boolean checkCollected(int id){
        return mISQLiteBehavior.checkCollected(id);
    }

    public void saveCollectQuestion(QuestionItem q){
        mISQLiteBehavior.saveCollectQuestion(q);
    }

    public void insertQuestion2Table(String tableName,QuestionItem q){
//        mISQLiteBehavior.addQuestionItem2Table(tableName,q);
            String sql = "INSERT INTO " + tableName
                    + "(id,question,answer,item1,item2,item3,item4,explains,url) " +
                    "VALUES ("+ q.getId() + ",'" + q.getQuestion() + "'," + "'" + q.getAnswer() + "'," +
                    "'" + q.getItem1() + "',"+ "'" + q.getItem2() + "',"+"'" + q.getItem3() + "',"+
                    "'" + q.getItem4() + "',"+ "'" + q.getExplains() + "',"+"'" + q.getUrl() + "')";
            mDB.execSQL(sql);
    }

    public void deleteQuestionById(int id){
        mISQLiteBehavior.deleteQuestionById(id);
    }

}
