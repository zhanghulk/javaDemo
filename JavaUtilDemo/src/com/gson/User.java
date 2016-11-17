package com.gson;

class User {
	public User(String name, int age, StringBuffer sex, boolean isChild) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.isChild = isChild;
	}

	private String name;
	private int age;
	private StringBuffer sex;
	private boolean isChild;

	public String toString() {
		return "{name=" + name + ";age=" + age + ";sex=" + sex + ";isChild=" + isChild + "}";
	}

	public int hashCode() {
		return name.hashCode() * 100 + age;
	}
}