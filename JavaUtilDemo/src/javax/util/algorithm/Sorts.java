package javax.util.algorithm;

import java.util.Arrays;

public class Sorts {
	public static void main(String[] args) {
		int[] arr = {8,4,7,0,9,2,3,1, 1,4,76,98,32,42,5};
		int[] sorted = insertionSort(arr);
		System.out.println(Arrays.toString(sorted));
	}
	// {6,4,7,0,9,2,3,1};
	public static int[] insertionSort(int[] arr) {
		int[] tmp = arr;
		for (int j = 1; j < arr.length; j++) {
			int key = arr[j];   // 每次循环key都选择下一个位置的值
			int i = j - 1;
			while (i >= 0 && arr[i] > key) {
				//如果目标值key比其左边的值小，说明key需要右移
				//直到插入到已经排好的子数组中合适的位置
				arr[i+1] = arr[i];
				
				//已排好的子数组中，后面开始，比key大的数值后移左移.
				i = i-1;
			}
			//i+1的值<=key，此位置就是key的目标位置
			arr[i+1] = key;
		}
		return tmp;
	}
	
	public static int[] insertionSort2(int[] arr) {
		int[] tmp = arr;
		for (int j = 1; j < arr.length; j++) {
			int key = arr[j];   // 每次循环key都选择下一个位置的值
			int i = j - 1;
			while (i >= 0 && arr[i] > key) {
				//如果目标值key比其左边的值小，说明key需要右移
				//直到插入到已经排好的子数组中合适的位置
				arr[i+1] = arr[i];
				
				//已排好的子数组中，后面开始，比key大的数值后移左移.
				i = i-1;
			}
			//i+1的值<=key，此位置就是key的目标位置
			arr[i+1] = key;
		}
		return tmp;
	}
}
