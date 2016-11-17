package com.gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Java 开发中Gson实现自定义类型(User)、java标准类型（集合、数组、列表、map等）对象之间的相互装换
 * 
 * @author zhanghao
 *
 */
public class GsonTest {
	public static void main(String[] args) {
		System.out.println("Java 开发中Gson实现自定义类型(User)、java标准类型（集合、数组、列表、map等）对象之间的相互装换**************************");
		Gson gson = new Gson();

		System.out.println("1    普通的Bean的转换**************************");
		System.out.println("将一个Bean转换成为json字符串->");
		User user1 = new User("凤姐", 12, new StringBuffer("未知"), true);
		System.out.println("转换之前的user1" + user1);
		String json = gson.toJson(user1);
		System.out.println("User对象转换成为Json字符串，json===" + json);

		System.out.println("***************************");
		System.out.println("将一个json字符串转换成为Bean->");
		User user2 = gson.fromJson(json, User.class);
		System.out.println("转换成为的user2==" + user2);
		System.out.println();

		System.out.println("2    Collection集合的转换**************************");
		System.out.println("将一个Bean的List集合转换成为json字符串->");
		Collection<User> userList1 = new ArrayList<User>();
		for (int i = 0; i < 3; i++) {
			User user = new User("如花" + i, 10 + i, new StringBuffer("男"), false);
			userList1.add(user);
		}
		json = gson.toJson(userList1);
		System.out.println("User的List集合对象转换成为Json字符串，json===" + json);

		System.out.println("***************************");
		System.out.println("将一个json字符串转换成为Bean的List集合->");
		Collection<User> userList2 = gson.fromJson(json, new TypeToken<Collection<User>>() {
		}.getType());
		System.out.println("转换成为的User的List集合，userList2=" + userList2);
		System.out.println();

		System.out.println("3   Array数组的转换**************************");
		System.out.println("将一个Bean的Array数组转换成为json字符串->");
		User[] userArray1 = new User[3];
		for (int i = 0; i < userArray1.length; i++) {
			userArray1[i] = new User("芙蓉" + i, 20 + i, new StringBuffer("人妖" + i), true);
		}
		json = gson.toJson(userArray1);
		System.out.println("User的数组对象转换成为Json字符串,json===" + json);

		System.out.println("***************************");
		System.out.println("将一个json字符串转换成为Bean的数组对象->");
		User[] userArray2 = gson.fromJson(json, new TypeToken<User[]>() {
		}.getType());
		System.out.println("转换成为的User的数组对象,userArray2=" + Arrays.toString(userArray2));
		System.out.println();

		System.out.println("4    Map的转换**************************");
		System.out.println("将一个Bean的Map转换成为json字符串->");
		Map<String, User> map1 = new HashMap<String, User>();
		for (int i = 0; i < 3; i++) {
			map1.put("" + (i + 10), userArray1[i]);
		}
		json = gson.toJson(map1);
		System.out.println("User的Map集合转换成为Json字符串,json===" + json);

		System.out.println("***************************");
		System.out.println("将一个json字符串转换成为Bean的数组对象->");
		Map<String, User> map2 = gson.fromJson(json, new TypeToken<Map<String, User>>() {
		}.getType());
		System.out.println("转换成为的User的数组对象，map2==" + map2);
	}
}