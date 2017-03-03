package com.driver.go.control;

import android.database.Cursor;

import com.driver.go.entity.QuestionItem;

/**
 * Created by malijie on 2017/2/24.
 */

public class EntityConvertManager {
    public static QuestionItem getOrderQuestionItemEntity(Cursor cursor){
        QuestionItem questionItem = new QuestionItem();
        questionItem.setId(cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(0))));
        questionItem.setQuestion(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
        questionItem.setAnswer(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
        questionItem.setItem1(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));
        questionItem.setItem2(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4))));
        questionItem.setItem3(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(5))));
        questionItem.setItem4(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(6))));
        questionItem.setExplains(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(7))));
        questionItem.setUrl(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(8))));
        return questionItem;
    }
    public static QuestionItem getQuestionItemEntity(Cursor cursor){
        QuestionItem questionItem = new QuestionItem();
        questionItem.set_id(cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(0))));
        questionItem.setId(cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(1))));
        questionItem.setQuestion(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
        questionItem.setAnswer(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));
        questionItem.setItem1(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4))));
        questionItem.setItem2(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(5))));
        questionItem.setItem3(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(6))));
        questionItem.setItem4(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(7))));
        questionItem.setExplains(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(8))));
        questionItem.setUrl(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(9))));
        return questionItem;
    }
}
