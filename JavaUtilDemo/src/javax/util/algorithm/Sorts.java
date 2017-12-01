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
			int key = arr[j];   // ÿ��ѭ��key��ѡ����һ��λ�õ�ֵ
			int i = j - 1;
			while (i >= 0 && arr[i] > key) {
				//���Ŀ��ֵkey������ߵ�ֵС��˵��key��Ҫ����
				//ֱ�����뵽�Ѿ��źõ��������к��ʵ�λ��
				arr[i+1] = arr[i];
				
				//���źõ��������У����濪ʼ����key�����ֵ��������.
				i = i-1;
			}
			//i+1��ֵ<=key����λ�þ���key��Ŀ��λ��
			arr[i+1] = key;
		}
		return tmp;
	}
	
	public static int[] insertionSort2(int[] arr) {
		int[] tmp = arr;
		for (int j = 1; j < arr.length; j++) {
			int key = arr[j];   // ÿ��ѭ��key��ѡ����һ��λ�õ�ֵ
			int i = j - 1;
			while (i >= 0 && arr[i] > key) {
				//���Ŀ��ֵkey������ߵ�ֵС��˵��key��Ҫ����
				//ֱ�����뵽�Ѿ��źõ��������к��ʵ�λ��
				arr[i+1] = arr[i];
				
				//���źõ��������У����濪ʼ����key�����ֵ��������.
				i = i-1;
			}
			//i+1��ֵ<=key����λ�þ���key��Ŀ��λ��
			arr[i+1] = key;
		}
		return tmp;
	}
}
