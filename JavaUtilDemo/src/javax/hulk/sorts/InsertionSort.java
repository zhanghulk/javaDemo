package javax.hulk.sorts;

import java.util.Arrays;

/**
 * 插入排序法：　从第二个元素指针开始，取当前位置向前移动遍历，每次循环中，发现当前元素大于关键值，依次后移．
 * 找到小于等于关键字的位置，就把关键字插入此位置(前面的部分已经排序完成了，没有必要继续往前遍历),
 * 逐步把关键值前面的元素都排序完成，直到完成排序
 * @author hulk
 *
 */
public class InsertionSort {
	
	static int[] ARR = {8,4,7,0,9,2,3,1, 1,4,76,98,32,42,5};
	public static void main(String[] args) {
		System.out.println(Arrays.toString(ARR));
		int[] sorted;
		sorted = sort(ARR);
		//sorted = sortDescending(ARR);
		System.out.println(Arrays.toString(sorted));
	}
	
	// {6,4,7,0,9,2,3,1};
	/**
	 * 插入排序法：　从第二个元素指针开始，取当前位置向前移动遍历，每次循环中，发现当前元素大于关键值，依次后移．
	 * 找到小于等于关键字的位置，就把关键字插入此位置(前面的部分已经排序完成了，没有必要继续往前遍历),
	 * 逐步把关键值前面的元素都排序完成，直到完成排序
	 * @param ARR
	 * @return
	 */
	public static int[] sort(int[] arr) {
		int[] tmp = arr;
		for (int j = 1; j < arr.length; j++) {
			int key = arr[j];   //当前位置为目标关键值
			int i = j - 1;
			//当前位置向前遍历，逐步把关键值前面的元素都排序完成,
			//找到小于等于关键字的位置，就把关键字插入此位置(前面的部分已经排序完成了，没有必要继续往前遍历),直到完成排序
			while (i >= 0 && arr[i] > key) {
				//发现比关键值大的元素就把元素依次后移
				arr[i+1] = arr[i];
				i--;
			}
			//发现当前元素值小于等于比关键值，把关键值插入到移动后的当前位置
			arr[i+1] = key;
		}
		return tmp;
	}
	
	public static int[] sortDescending(int[] arr) {
		int[] tmp = arr;
		for (int j = 1; j < arr.length; j++) {
			int key = arr[j];   //当前位置为目标关键值
			int i = j - 1;
			//当前位置向前遍历，逐步把关键值前面的元素都排序完成,
			//找到小于等于关键字的位置，就把关键字插入此位置(前面的部分已经排序完成了，没有必要继续往前遍历),直到完成排序
			while (i >= 0 && arr[i] < key) {
				//发现比关键值小的元素就把次元素后移
				arr[i+1] = arr[i];
				i--;
			}
			//发现当前元素值大于等于比关键值，把关键值插入到移动后的当前指针位置
			arr[i+1] = key;
		}
		return tmp;
	}
}
