package com.hulk.utils;

public class Log {

	public static void i(String tag, String msg) {
		System.out.println(tag + ":" + msg);
	}
	
	public static void w(String tag, String msg) {
		System.out.println(tag + ":" + msg);
	}
	
	public static void e(String tag, String msg, Exception e) {
		System.out.println(tag + ":" + msg);
		e.printStackTrace();
	}
}
