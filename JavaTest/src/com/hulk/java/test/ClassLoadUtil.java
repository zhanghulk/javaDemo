package com.hulk.java.test;

public class ClassLoadUtil {
	
	static {
		System.out.println("ClassLoadUtil: static ......");
		int aa = 9;
		int bb = 0;
		int cc = aa / bb;
		//改成0,就出现崩溃;
		/*
		ClassLoadTest: main ......
		ClassLoadTest: ready call ClassLoadUtil.init ......
		ClassLoadUtil: static ......
		Exception in thread "main" java.lang.ExceptionInInitializerError
			at com.hulk.java.test.ClassLoadTest.main(ClassLoadTest.java:13)
		Caused by: java.lang.ArithmeticException: / by zero
			at com.hulk.java.test.ClassLoadUtil.<clinit>(ClassLoadUtil.java:9)
			... 1 more
		 * */
		System.out.println("ClassLoadUtil: cc = " + cc);
	}
	
	public static void init() {
		System.out.println("ClassLoadUtil: called init ......");
	}
}
