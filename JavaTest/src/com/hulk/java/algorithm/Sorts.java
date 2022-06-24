package com.hulk.java.algorithm;

import java.util.Arrays;

public class Sorts {

	private static int[] ARR = {21, 1,23,45,76,29, 9, 6, 11, 6, 5, 2, 15, 10, 13, 8, 7,4};
	private static int[] ARR2 = {21, 1, 23, 45,76,29,9, 6, 11, 6, 5, 2, 15, 10, 13, 8, 7,4};

	public static void main(String[] args) {
		insertSort(ARR);
		shellSort(ARR2);
	}

	/**
	 * 插入排序法
	 * <p>直接插入排序
	 * 在排序之前我们需要搞清一个思路: 新插入一个数据的时候，排序过后的数组都是从小到大排列好的，
	 * 所以我们需要从后往前查找，直到找到比我们要插入的数字还小的值。这个时候我们需要一个变量j作为标识
	 * @param arr
	 */
	public static void insertSort(int[] arr) {
		if(arr == null) {
			return;
		}
		int count = 0;//记录循环次数，无实际意义
		int length = arr.length;
		for (int i = 0; i < length; i++) {
			int temp = arr[i];
			//向前查找合适的位置
			int j;
			for(j = i - 1; j >= 0; j--) {
				if(arr[j] > temp) {
					//往后查找，找到比第i个元素大的元素往后平移一步
					arr[j + 1] = arr[j];
				} else {
					//记录j的值,也就是temp要插入的位置， 注意此处的break为必须的，否则会导致j的值过于超前，导致错位
					break;
				}
				//count计数器加1
				count++;
			}
			//j + 1s是因为for循环最后会--，所以需要+1
			arr[j + 1] = temp;
		}
		System.out.println("Insert sort: " + Arrays.toString(arr));
		System.out.println("Array length: " + length + "， loop loopCount: " + count);
	}

	/**
	 * 希尔排序
	 * 希尔排序时,对有序序列在插入时采用交换法
	 * @param arr
	 */
	public static void shellSort(int[] arr) {
		if(arr == null) {
			return;
		}
		int count = 0;//记录循环次数，无实际意义
		int temp;
		int length = arr.length;
		for(int gap = length / 2; gap > 0; gap = gap / 2) {
			for(int i = gap; i < length; i++) {
				// 遍历各组中所有的元素(共gap组，每组有个元素), 步长gap
				for(int j = i - gap; j >= 0; j = j - gap) {
					//交换法:  如果当前元素大于加上步长后的那个元素，说明交换,打得放后面
					if(arr[j] > arr[j + gap]) {
						temp = arr[j];
						arr[j] = arr[j + gap];
						arr[j + gap] = temp;
					} else {
						//交换法时，此处的break非必要，但是能减少循环次数
						break;
					}
					
					//计数器加1，无实际意义
					count++;
				}
			}
		}
		
		System.out.println("Shell  sort: " + Arrays.toString(arr));
		System.out.println("Array length: " + length + "， loop loopCount: " + count);
	}
}
