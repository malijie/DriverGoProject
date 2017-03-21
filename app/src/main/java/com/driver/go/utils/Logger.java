package com.driver.go.utils;

import android.util.Log;

public class Logger {
	private static boolean mLogSwitch = true;
	
	public static void setLogSwitch(boolean isDebug){
		mLogSwitch = isDebug;
	}
	
	public static boolean getLogSwitch(){
		return mLogSwitch;
	}
	
	public static void v(String tag, String msg){
		if(getLogSwitch()){
			Log.v(tag, msg);
		}
	}
	
	public static void e(String tag, String msg){
		if(getLogSwitch()){
			Log.e(tag, msg);
		}
	}
	
	public static void i(String tag, String msg){
		if(getLogSwitch()){
			Log.i(tag, msg);
		}
	}
	
	public static void d(String tag, String msg){
		if(getLogSwitch()){
			Log.d(tag, msg);
		}
	}

	public static void mlj(String msg){
		if(getLogSwitch()){
			Log.d("MLJ", msg);
		}
	}
	
	public static void w(String tag, String msg){
		if(getLogSwitch()){
			Log.w(tag, msg);
		}
	}
}
