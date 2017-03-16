package com.driver.go.utils;

import android.content.Context;

import com.driver.go.base.DriverGoApplication;

/**
 * Created by malijie on 2016/10/10.
 */
public class SharePreferenceUtil {
    public static final String SP_USER_INFO = "user_info";
    public static final String SUBJECT1_ORDER_QUESTION_CURRENT_INDEX_KEY = "subject1_order_question_index";
    public static final String SUBJECT1_RECITE_QUESTION_CURRENT_INDEX_KEY = "subject1_recite_question_index";
    public static final String SUBJECT4_ORDER_QUESTION_CURRENT_INDEX_KEY = "subject4_order_question_index";
    public static final String SUBJECT4_RECITE_QUESTION_CURRENT_INDEX_KEY = "subject4_recite_question_index";

    public static void saveSubject1OrderQuestionIndex(int index){
            DriverGoApplication.sContext.getSharedPreferences(SP_USER_INFO, Context.MODE_PRIVATE).edit().putInt(SUBJECT1_ORDER_QUESTION_CURRENT_INDEX_KEY,index).commit();
    }

    public static int loadSubject1OrderQuestionIndex(){
       return DriverGoApplication.sContext.getSharedPreferences(SP_USER_INFO, Context.MODE_PRIVATE).getInt(SUBJECT1_ORDER_QUESTION_CURRENT_INDEX_KEY,1);
    }


    public static void saveSubject1ReciteQuestionIndex(int index) {
        DriverGoApplication.sContext.getSharedPreferences(SP_USER_INFO, Context.MODE_PRIVATE).edit().putInt(SUBJECT1_RECITE_QUESTION_CURRENT_INDEX_KEY,index).commit();
    }

    public static int loadSubject1ReciteQuestionIndex(){
        return DriverGoApplication.sContext.getSharedPreferences(SP_USER_INFO, Context.MODE_PRIVATE).getInt(SUBJECT1_RECITE_QUESTION_CURRENT_INDEX_KEY,1);
    }

    public static void saveSubject4OrderQuestionIndex(int index){
        DriverGoApplication.sContext.getSharedPreferences(SP_USER_INFO, Context.MODE_PRIVATE).edit().putInt(SUBJECT4_ORDER_QUESTION_CURRENT_INDEX_KEY,index).commit();
    }

    public static int loadSubject4OrderQuestionIndex(){
        return DriverGoApplication.sContext.getSharedPreferences(SP_USER_INFO, Context.MODE_PRIVATE).getInt(SUBJECT4_ORDER_QUESTION_CURRENT_INDEX_KEY,1);
    }


    public static void saveSubject4ReciteQuestionIndex(int index) {
        DriverGoApplication.sContext.getSharedPreferences(SP_USER_INFO, Context.MODE_PRIVATE).edit().putInt(SUBJECT4_RECITE_QUESTION_CURRENT_INDEX_KEY,index).commit();
    }

    public static int loadSubject4ReciteQuestionIndex(){
        return DriverGoApplication.sContext.getSharedPreferences(SP_USER_INFO, Context.MODE_PRIVATE).getInt(SUBJECT4_RECITE_QUESTION_CURRENT_INDEX_KEY,1);
    }



}
