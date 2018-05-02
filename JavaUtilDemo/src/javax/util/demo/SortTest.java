package javax.util.demo;

import java.util.Arrays;

public class SortTest {

	static int[] array = {7, 3, 6, 9, 2, 5, 8, 12, 23, 4, 1, 54};
	public static void main(String[] args) {
		System.out.println(Arrays.toString(array));
		quickSort(array, 0, array.length - 1);
		System.out.println(Arrays.toString(array));
	}

	private static void quickSort(int[] array, int startIndex, int endIndex) {
		if(startIndex >= endIndex) {
			//递归结束点：　当左右相等或者左大于右时结束函数
			//System.out.println("End same startIndex = " + startIndex + ", endIndex= " + endIndex);
			return;
		}
		int boundary = boundary(array, startIndex, endIndex);
		System.out.println(Arrays.toString(array) + " boundary = " + boundary);
		//递归排序数组前面部分子数组，先完成前面部分排序
		quickSort(array, startIndex, boundary - 1);
		
		//递归后面部分的排序
		quickSort(array, boundary + 1, endIndex);
	}
	
	/**
	 * 寻找分界点的标记下标
	 * 每一次以第一个为基准值，分界点后面部分中较小的值和前面部分中较大的值进行交换
	 * @param array
	 * @param startIndex
	 * @param endIndex
	 */
	private static int boundary(int[] array, int startIndex, int endIndex) {
		int standard = array[startIndex];//取第一个值为基准值
		int leftIndex = startIndex;//左指针
		int rightIndex = endIndex;//右指针
		
		//交换leftIndex和rightIndex的值
		while(leftIndex < rightIndex) {
			//从后面开始往前搜索
			while(leftIndex < rightIndex && array[rightIndex] >= standard) {
				//发现值比基准值大就略过
				rightIndex--;
			}
			//后面的值比基准值小，把这个值移到最前面的leftIndex位置，rightIndex的位置成为＂空＂
			array[leftIndex] = array[rightIndex];
			System.out.println(Arrays.toString(array) + " rightIndex = " + rightIndex + ", leftIndex= " + leftIndex);
			
			//前面开始检索
			while(leftIndex < rightIndex && array[leftIndex] <= standard) {
				//发现值比基准值小就略过
				leftIndex++;
			}
			//前面的值比基准值大，就把这个值移到rightIndex位置，　leftIndex的位置成为＂空＂
			array[rightIndex] = array[leftIndex];
			System.out.println(Arrays.toString(array) + " leftIndex = " + leftIndex + ", rightIndex= " + rightIndex);
		}
		
		//左边的＂空＂位置赋值为基准值
		array[leftIndex] = standard;
		return leftIndex;
	}
}
