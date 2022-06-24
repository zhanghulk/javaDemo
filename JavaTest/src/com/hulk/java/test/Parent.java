package com.hulk.java.test;

public class Parent{
	 static int id = 1000;

	 static {
	  System.out.println("=== parent static code block ===");
	 }

	 {
	  System.out.println("=== parent normal code black ===");
	 }

	 public Parent(){
	  System.out.println(" === parent static id = " + id);
	 }

	public static void main(String[] args){
		System.out.println("======== 111111111 Before load child ========");
		Child child = new Child();
		System.out.println("======== 22222222222 test line ========");
		child = new Child();
		System.out.println("======== 3333333333 test line ========");
	}
}