package com.driver.go.http;

/**
 * Created by malijie on 2017/2/22.
 */

public class URLContainer {
    private static final String BASE_URL = "http://v.juhe.cn/";
    private static final String APP_KEY = "81f85312a3cea93426e336681c734b0f";

    public static final String getAppKey(){
        return APP_KEY;
    }

    public static String getBaseURL(){
        return BASE_URL;
    }
}
