package javax.util.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraySpliter {

	public static void main(String[] args) {

		int[] ary = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };// 要分割的数组
		int splitSize = 5;// 分割的块大小
		String[] arys = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13","14","15","16","17","18","19","20" };// 要分割的数组
		Object[] subAry = splitArrayInt(ary, splitSize);// 分割后的子块数组

		for (Object obj : subAry) {// 打印输出结果

			int[] aryItem = (int[]) obj;

			for (int i = 0; i < aryItem.length; i++) {
				System.out.print(aryItem[i] + ", ");
			}
			System.out.println();
		}

		List<List<String>> subArys = splitArrayAsList(arys, splitSize);// 分割后的子块数组
		for (List<String> list : subArys) {
			List<String> subList = list;
			for (String string : subList) {
				System.out.print(string + ", ");
			}
			System.out.println();
		}
		
		Object[][] subAryo = splitArrayObject(arys, splitSize);// 分割后的子块数组
		for (Object[] objects : subAryo) {
			for (Object object : objects) {
				System.out.print(object + ", ");
			}
			System.out.println();
		}
	}

	public static int[][] splitArrayInt(int[] array, int subSize) {
		int count = array.length % subSize == 0 ? array.length / subSize
				: array.length / subSize + 1;

		List<List<Integer>> subAryList = new ArrayList<List<Integer>>();

		for (int i = 0; i < count; i++) {
			int index = i * subSize;

			List<Integer> list = new ArrayList<Integer>();
			int j = 0;
			while (j < subSize && index < array.length) {
				list.add(array[index++]);
				j++;
			}

			subAryList.add(list);
		}

		int[][] subAry = new int[subAryList.size()][subSize];

		for (int i = 0; i < subAryList.size(); i++) {
			List<Integer> subList = subAryList.get(i);

			int[] subAryItem = new int[subList.size()];
			for (int j = 0; j < subList.size(); j++) {
				subAryItem[j] = subList.get(j).intValue();
			}

			subAry[i] = subAryItem;
		}

		return subAry;
	}

	public static <T> List<List<T>> splitArrayAsList(T[] array, int subSize) {
		int count = array.length % subSize == 0 ? array.length / subSize
				: array.length / subSize + 1;

		List<List<T>> subAryList = new ArrayList<List<T>>();

		for (int i = 0; i < count; i++) {
			int index = i * subSize;

			List<T> list = new ArrayList<T>();
			int j = 0;
			while (j < subSize && index < array.length) {
				list.add(array[index++]);
				j++;
			}

			subAryList.add(list);
		}

		return subAryList;
	}

	public static Object[][] splitArrayObject(Object[] array, int subSize) {
		int count = array.length % subSize == 0 ? array.length / subSize
				: array.length / subSize + 1;

		List<List<Object>> subAryList = new ArrayList<List<Object>>();

		for (int i = 0; i < count; i++) {
			int index = i * subSize;

			List<Object> list = new ArrayList<Object>();
			int j = 0;
			while (j < subSize && index < array.length) {
				list.add(array[index++]);
				j++;
			}

			subAryList.add(list);
		}

		Object[][] subAry = new Object[subAryList.size()][subSize];

		for (int i = 0; i < subAryList.size(); i++) {
			List<Object> subList = subAryList.get(i);

			Object[] subAryItem = new Object[subList.size()];
			for (int j = 0; j < subList.size(); j++) {
				subAryItem[j] = subList.get(j);
			}

			subAry[i] = subAryItem;
		}

		return subAry;
	}
}
