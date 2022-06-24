package com.hulk.java.hash;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class HashMapTest {
	
	public static final int COUNT = 10000000;

	public static void main(String[] args) {
		//testHashCode();
		runTask(new Runnable() {
			public void run() {
				HashMap<Integer, User> hashMap = new HashMap<Integer, User>();
				System.out.println("create HashMap");
				createMap(hashMap, COUNT);
				
				long start = System.currentTimeMillis();
				System.out.println("get from hashMap: " + hashMap.get(COUNT / 2));
				long costSeconds = (System.currentTimeMillis() - start);
				System.out.println("Get hashMap Cost time: " + costSeconds);
			}
		});
		
		runTask(new Runnable() {
			public void run() {
				System.out.println("create Hashtable");
				Hashtable<Integer, User> table = new Hashtable<Integer, User>();
				createMap(table, COUNT);
				
				long start = System.currentTimeMillis();
				System.out.println("get from Hashtable: " + table.get(COUNT / 2));
				long costSeconds = (System.currentTimeMillis() - start);
				System.out.println("Get Hashtable Cost time: " + costSeconds);
			}
		});
	}
	
	public static void runTask(Runnable task) {
		new Thread(task).start();
	}
	
	public static void createMap(Map<Integer, User> map, int count) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			User user = new User();
			user.id = i;
			map.put(user.id,  user);
		}
		long costSeconds = (System.currentTimeMillis() - start);
		System.out.println("createMap: Cost time: " + costSeconds);
	}
	
	/**
	 * 改变对象的字段，对象的hash code未发生变化
	 */
	public static void testHashCode() {
		User user = new User();
		System.out.println(user.hashCode());
		//改变对象的字段，对象的hash code未发生变化
		user.name = "hao";
		System.out.println(user.hashCode());
	}

	public static class User {
		int id = 0;
		String name = "hao";
		String name2 = "hao2";
		String name3 = "hao3";
		String name4 = "hao4";
		String name5 = "hao5";
		String name6 = "hao6";
	}
}
