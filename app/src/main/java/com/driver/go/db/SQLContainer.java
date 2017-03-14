package com.driver.go.db;


/**
 * Created by malijie on 2017/2/22.
 */

public class SQLContainer {

    public static String getCreateOrderExamTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.ORDER_EXAM_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id int,question varchar(100)," +
                "answer varchar(10), item1 varchar(100),item2 varchar(100)," +
                "item3 varchar(100),item4 varchar(100),explains varchar(400)," +
                "url varchar(100))";
    }

    public static String getCreateRandomExamTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.RANDOM_EXAM_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id int,question varchar(100)," +
                "answer varchar(10), item1 varchar(100),item2 varchar(100)," +
                "item3 varchar(100),item4 varchar(100),explains varchar(400)," +
                "url varchar(100))";
    }

    public static String getCreateWrongQuestionTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.PRACTISE_WRONG_QUESTION_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id int,question varchar(100)," +
                "answer varchar(10), item1 varchar(100),item2 varchar(100)," +
                "item3 varchar(100),item4 varchar(100),explains varchar(400)," +
                "url varchar(100))";
    }

    public static String getCreateCollectQuestionTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.COLLECT_QUESTION_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id int,question varchar(100)," +
                "answer varchar(10), item1 varchar(100),item2 varchar(100)," +
                "item3 varchar(100),item4 varchar(100),explains varchar(400)," +
                "url varchar(100))";
    }

    public static String getCreateExamWrongQuestionTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.EXAM_WRONG_QUESTION_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id int,question varchar(100)," +
                "answer varchar(10), item1 varchar(100),item2 varchar(100)," +
                "item3 varchar(100),item4 varchar(100),explains varchar(400)," +
                "url varchar(100))";
    }

    public static String getCreateC1ExamRecordTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.C1_EXAM_RECORD_TABLE + "(score int,date varchar(100))";
    }

    public static String getFirstOrderExamDataSQL(){
          return "SELECT * FROM " + DBConstants.ORDER_EXAM_TABLE + " WHERE id=1";
    }

    public static String getOrderExamItemByIdSQL(int id){
        return  "SELECT * FROM " + DBConstants.ORDER_EXAM_TABLE + " WHERE id=" + id;
    }

    public static String getRandomExamItemByIdSQL(int id){
        return  "SELECT * FROM " + DBConstants.RANDOM_EXAM_TABLE + " WHERE _id=" + id;
    }

    public static String getCollectQuestionItemByIdSQL(int id){
        return  "SELECT * FROM " + DBConstants.COLLECT_QUESTION_TABLE + " WHERE id=" + id;
    }

    public static String getRandomQuestionByIndexSQL(int index){
        return  "SELECT * FROM " + DBConstants.RANDOM_EXAM_TABLE + " WHERE _id=" + index;
    }


    public static String getDeleteTableSQL(String tableName){
        return "DELETE FROM " + tableName;
    }


    public static String getAllDataSQL(String tableName){
        return "SELECT * FROM " + tableName;
    }

    public static String getDeleteQuestionSQL(String tableName, int id){
        return "DELETE FROM " + tableName + " WHERE id=" + id;
    }

    public static String getInsertExamRecordDataSQL(String tableName,String date,int score){
        return "INSERT INTO " + tableName + " VALUES(" + score + ",'" +date + "')";
    }
}
