package com.driver.go.utils;

import android.content.Context;

import com.driver.go.base.DriverGoApplication;

/**
 * Created by malijie on 2016/10/10.
 */
public class SharePreferenceUtil {
    public static final String SP_USER_INFO = "user_info";
    public static final String ORDER_QUESTION_CURRENT_INDEX_KEY = "order_question_index";
    public static final String RANDOM_QUESTION_CURRENT_INDEX_KEY = "random_question_index";
    public static final String RECITE_QUESTION_CURRENT_INDEX_KEY = "recite_question_index";

    public static void saveOrderQuestionIndex(int index){
            DriverGoApplication.sContext.getSharedPreferences(SP_USER_INFO, Context.MODE_PRIVATE).edit().putInt(ORDER_QUESTION_CURRENT_INDEX_KEY,index).commit();
    }

    public static int loadOrderQuestionIndex(){
       return DriverGoApplication.sContext.getSharedPreferences(SP_USER_INFO, Context.MODE_PRIVATE).getInt(ORDER_QUESTION_CURRENT_INDEX_KEY,1);
    }


    public static void saveRandomQuestionIndex(int index){
        DriverGoApplication.sContext.getSharedPreferences(SP_USER_INFO, Context.MODE_PRIVATE).edit().putInt(RANDOM_QUESTION_CURRENT_INDEX_KEY,index).commit();
    }

    public static int loadRandomQuestionIndex(){
        return DriverGoApplication.sContext.getSharedPreferences(SP_USER_INFO, Context.MODE_PRIVATE).getInt(RANDOM_QUESTION_CURRENT_INDEX_KEY,1);
    }

    public static void saveReciteQuestionIndex(int index) {
        DriverGoApplication.sContext.getSharedPreferences(SP_USER_INFO, Context.MODE_PRIVATE).edit().putInt(RECITE_QUESTION_CURRENT_INDEX_KEY,index).commit();
    }

    public static int loadReciteQuestionIndex(){
        return DriverGoApplication.sContext.getSharedPreferences(SP_USER_INFO, Context.MODE_PRIVATE).getInt(RECITE_QUESTION_CURRENT_INDEX_KEY,1);
    }



}