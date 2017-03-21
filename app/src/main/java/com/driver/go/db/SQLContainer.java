package com.driver.go.db;


/**
 * Created by malijie on 2017/2/22.
 */

public class SQLContainer {
    //--------------------------------------------------科目一----------------------------------------------

    public static String getCreateSubject1OrderExamTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.SUBJECT1_ORDER_PRACTISE_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id int,question varchar(100)," +
                "answer varchar(10), item1 varchar(100),item2 varchar(100)," +
                "item3 varchar(100),item4 varchar(100),explains varchar(400)," +
                "url varchar(100))";
    }

    public static String getCreateSubject1RandomExamTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.SUBJECT1_RANDOM_PRACTISE_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id int,question varchar(100)," +
                "answer varchar(10), item1 varchar(100),item2 varchar(100)," +
                "item3 varchar(100),item4 varchar(100),explains varchar(400)," +
                "url varchar(100))";
    }

    public static String getCreateSubject1WrongQuestionTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.SUBJECT1_PRACTISE_WRONG_QUESTION_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id int,question varchar(100)," +
                "answer varchar(10), item1 varchar(100),item2 varchar(100)," +
                "item3 varchar(100),item4 varchar(100),explains varchar(400)," +
                "url varchar(100))";
    }

    public static String getCreateSubject1CollectQuestionTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.SUBJECT1_COLLECT_QUESTION_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id int,question varchar(100)," +
                "answer varchar(10), item1 varchar(100),item2 varchar(100)," +
                "item3 varchar(100),item4 varchar(100),explains varchar(400)," +
                "url varchar(100))";
    }

    public static String getCreateSubject1ExamWrongQuestionTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.SUBJECT1_EXAM_WRONG_QUESTION_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id int,question varchar(100)," +
                "answer varchar(10), item1 varchar(100),item2 varchar(100)," +
                "item3 varchar(100),item4 varchar(100),explains varchar(400)," +
                "url varchar(100))";
    }

    public static String getCreateSubject1ExamRecordTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.SUBJECT1_C1_EXAM_RECORD_TABLE + "(score int,date varchar(100))";
    }

    public static String getSubject1FirstOrderExamDataSQL(){
          return "SELECT * FROM " + DBConstants.SUBJECT1_ORDER_PRACTISE_TABLE + " WHERE id=1";
    }

    public static String getSubject1OrderExamItemByIdSQL(int id){
        return  "SELECT * FROM " + DBConstants.SUBJECT1_ORDER_PRACTISE_TABLE + " WHERE id=" + id;
    }

    public static String getSubject1RandomExamItemByIdSQL(int id){
        return  "SELECT * FROM " + DBConstants.SUBJECT1_RANDOM_PRACTISE_TABLE + " WHERE _id=" + id;
    }

    public static String getSubject1CollectQuestionItemByIdSQL(int id){
        return  "SELECT * FROM " + DBConstants.SUBJECT1_COLLECT_QUESTION_TABLE + " WHERE id=" + id;
    }

    public static String getSubject1RandomQuestionByIndexSQL(int index){
        return  "SELECT * FROM " + DBConstants.SUBJECT1_RANDOM_PRACTISE_TABLE + " WHERE _id=" + index;
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

    public static String getSubject1MaxScoreSQL(){
        return "SELECT MAX(score) from " + DBConstants.SUBJECT1_C1_EXAM_RECORD_TABLE;
    }

    //---------------------------------------------科目四-------------------------------

    public static String getCreateSubject4OrderExamTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.SUBJECT4_ORDER_EXAM_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id int,question varchar(100)," +
                "answer varchar(10), item1 varchar(100),item2 varchar(100)," +
                "item3 varchar(100),item4 varchar(100),explains varchar(400)," +
                "url varchar(100))";
    }

    public static String getCreateSubject4RandomExamTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.SUBJECT4_RANDOM_EXAM_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id int,question varchar(100)," +
                "answer varchar(10), item1 varchar(100),item2 varchar(100)," +
                "item3 varchar(100),item4 varchar(100),explains varchar(400)," +
                "url varchar(100))";
    }

    public static String getCreateSubject4WrongQuestionTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.SUBJECT4_PRACTISE_WRONG_QUESTION_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id int,question varchar(100)," +
                "answer varchar(10), item1 varchar(100),item2 varchar(100)," +
                "item3 varchar(100),item4 varchar(100),explains varchar(400)," +
                "url varchar(100))";
    }

    public static String getCreateSubject4CollectQuestionTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.SUBJECT4_COLLECT_QUESTION_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id int,question varchar(100)," +
                "answer varchar(10), item1 varchar(100),item2 varchar(100)," +
                "item3 varchar(100),item4 varchar(100),explains varchar(400)," +
                "url varchar(100))";
    }

    public static String getCreateSubject4ExamWrongQuestionTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.SUBJECT4_EXAM_WRONG_QUESTION_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id int,question varchar(100)," +
                "answer varchar(10), item1 varchar(100),item2 varchar(100)," +
                "item3 varchar(100),item4 varchar(100),explains varchar(400)," +
                "url varchar(100))";
    }

    public static String getCreateSubject4ExamRecordTableSQL(){
        return "CREATE TABLE IF NOT EXISTS " + DBConstants.SUBJECT4_C1_EXAM_RECORD_TABLE + "(score int,date varchar(100))";
    }

    public static String getSubject4FirstOrderExamDataSQL(){
        return "SELECT * FROM " + DBConstants.SUBJECT4_ORDER_EXAM_TABLE + " WHERE id=1";
    }

    public static String getSubject4OrderExamItemByIdSQL(int id){
        return  "SELECT * FROM " + DBConstants.SUBJECT4_ORDER_EXAM_TABLE + " WHERE id=" + id;
    }

    public static String getSubject4RandomExamItemByIdSQL(int id){
        return  "SELECT * FROM " + DBConstants.SUBJECT4_RANDOM_EXAM_TABLE + " WHERE _id=" + id;
    }

    public static String getSubject4CollectQuestionItemByIdSQL(int id){
        return  "SELECT * FROM " + DBConstants.SUBJECT4_COLLECT_QUESTION_TABLE + " WHERE id=" + id;
    }

    public static String getSubject4RandomQuestionByIndexSQL(int index){
        return  "SELECT * FROM " + DBConstants.SUBJECT4_RANDOM_EXAM_TABLE + " WHERE _id=" + index;
    }

    public static String getSubject4MaxScoreSQL(){
        return "SELECT MAX(score) from " + DBConstants.SUBJECT4_C1_EXAM_RECORD_TABLE;
    }
}
