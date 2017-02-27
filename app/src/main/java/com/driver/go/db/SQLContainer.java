package com.driver.go.db;

/**
 * Created by malijie on 2017/2/22.
 */

public class SQLContainer {

    public static String getCreateOrderExamTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.ORDER_EXAM_TABLE + "(id int,question varchar(100)," +
                "answer varchar(10), item1 varchar(100),item2 varchar(100)," +
                "item3 varchar(100),item4 varchar(100),explains varchar(400)," +
                "url varchar(100))";
    }

    public static String getCreateRandomExamTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.RANDOM_EXAM_TABLE + "(id int,question varchar(100)," +
                "answer varchar(10), item1 varchar(100),item2 varchar(100)," +
                "item3 varchar(100),item4 varchar(100),explains varchar(400)," +
                "url varchar(100))";
    }

    public static String getCreateWrongQuestionTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.WRONG_QUESTION_TABLE + "(id int,question varchar(100)," +
                "answer varchar(10), item1 varchar(100),item2 varchar(100)," +
                "item3 varchar(100),item4 varchar(100),explains varchar(400)," +
                "url varchar(100))";
    }

    public static String getCreateCollectQuestionTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.COLLECT_QUESTION_TABLE + "(id int,question varchar(100)," +
                "answer varchar(10), item1 varchar(100),item2 varchar(100)," +
                "item3 varchar(100),item4 varchar(100),explains varchar(400)," +
                "url varchar(100))";
    }

    public static String getFirstOrderExamDataSQL(){
          return "SELECT * FROM " + DBConstants.ORDER_EXAM_TABLE + " WHERE id=1";
    }

    public static String getOrderExamItemById(int id){
        return  "SELECT * FROM " + DBConstants.ORDER_EXAM_TABLE + " WHERE id=" + id;
    }

    public static String getCollectQuestionItemById(int id){
        return  "SELECT * FROM " + DBConstants.COLLECT_QUESTION_TABLE + " WHERE id=" + id;
    }
}
