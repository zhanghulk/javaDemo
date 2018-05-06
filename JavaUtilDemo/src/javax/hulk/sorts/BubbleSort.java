package javax.hulk.sorts;

import java.util.Arrays;

/**
 * 冒泡排序法：　每次循环都完成前面部分或者后面部分排序完成
 * @author hulk
 *
 */
public class BubbleSort {

	static int[] ARRAY_TEST = { 7, 87, 15, 3, 6, 9, 2, 5, 8, 12, 23, 4, 1, 54,34,21 };

	public static void main(String[] args) {
		testBubbleSort();
	}

	private static void testBubbleSort() {
		System.out.println(Arrays.toString(ARRAY_TEST));
		long startTime = System.currentTimeMillis();
		int swappedCount = 0;
		//swappedCount = sort(ARRAY_TEST);
		swappedCount = sort2(ARRAY_TEST);
		long deltaTiime = System.currentTimeMillis() - startTime;
		System.out.println(Arrays.toString(ARRAY_TEST) + ", swappedCount: " + swappedCount + ", deltaTiime: " + deltaTiime);
	}

	/**
	 * 外层循环由后面开始向前遍历数组，每次循环都把较大的数移植到后面，后面的排序完成之后就不用重复
	 * 内层循环由前向后进行遍历, 发现后一个比前面个小就交换位置
	 * @param a
	 */
	public static int sort(int[] a) {
		int temp = 0;
		int swappedCount = 0;
		for (int i = a.length - 1; i > 0; --i) {
			//内层循环由前向后进行遍历
			for (int j = 0; j < i; ++j) {
				if (a[j + 1] < a[j]) {
					//发现后一个比前面个小就交换位置
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
					swappedCount++;
				}
			}
		}
		return swappedCount;
	}
	
	/**
	 * 降序
	 * @param a
	 * @return
	 */
	public static int sortDescending(int[] a) {
		int temp = 0;
		int swappedCount = 0;
		for (int i = a.length - 1; i > 0; --i) {
			//内层循环由前向后进行遍历
			for (int j = 0; j < i; ++j) {
				if (a[j + 1] > a[j]) {
					//发现后一个比前面个小就交换位置
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
					swappedCount++;
				}
			}
		}
		return swappedCount;
	}
	
	/**
	 * 由前面开始遍历，把较小的值前移，前面的部分先排序，逐步向后移动，直至最后一个元素．
	 * @param array
	 */
	public static int sort2(int[] array) {
		int swappedCount = 0;
		int count = array.length;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < i; j++) {
				if (array[j] > array[i]) {
					// 发现后面的数值比前面的值大，就执行交换
					int temp = array[j];
					array[j] = array[i];
					array[i] = temp;
					//System.out.println("sort2 swap: i= " + i + ", j= " + j + " " + Arrays.toString(array));
					swappedCount++;
				}
			}
		}
		return swappedCount;
	}
	
	/**
	 * 降序
	 * @param array
	 * @return
	 */
	public static int sortDescending2(int[] array) {
		int swappedCount = 0;
		int count = array.length;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < i; j++) {
				if (array[j] < array[i]) {
					// 发现后面的数值比前面的值小，就执行交换
					int temp = array[j];
					array[j] = array[i];
					array[i] = temp;
					swappedCount++;
				}
			}
		}
		return swappedCount;
	}
}
