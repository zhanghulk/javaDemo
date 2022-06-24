package com.hulk.java.test;

public class Child {
	static int _id = 100;

	 static {
		 System.out.println("=== child static code block ===" + _id++);
	 }

	 {
		 System.out.println("=== child normal code black ===" + _id++);
	 }

	 public Child(){
		 super();
		 System.out.println(" === child static id = " + _id++);
	 }
}
