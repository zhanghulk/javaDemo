package com.hulk.java.test;

public class ClassLoadTest {
	
	public static final String TAG = "ClassLoadTest";
	
	public static void main(String[] args) {
		
		System.out.println(TAG + ": main ......");
		
		System.out.println(TAG + ": ready call ClassLoadUtil.init ......");
		
		ClassLoadUtil.init();
		testClassLoader();
	}
	
	private static void testClassLoader() {
		ClassLoader classLoader = Parent.class.getClassLoader();
		System.out.println("Parent class loader: " + classLoader);
	}
}
