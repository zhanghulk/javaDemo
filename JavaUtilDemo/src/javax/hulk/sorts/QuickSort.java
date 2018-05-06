package javax.hulk.sorts;

import java.util.Arrays;

public class QuickSort {

	static int[] ARRAY_TEST = { 7, 3, 56, 97, 6, 9, 2, 5, 8, 12, 23, 4, 1, 23, 65, 11, 54 };

	public static void main(String[] args) {
		testQuickSort();
	}

	private static void testQuickSort() {
		System.out.println(Arrays.toString(ARRAY_TEST));
		long startTime = System.currentTimeMillis();
		quickSort(ARRAY_TEST, 0, ARRAY_TEST.length - 1);
		//quickSortDescending(ARRAY_TEST, 0, ARRAY_TEST.length - 1);
		long deltaTiime = System.currentTimeMillis() - startTime;
		System.out.println(Arrays.toString(ARRAY_TEST) + ", deltaTiime: " + deltaTiime);
	}

	/**
	 * 递归实现快速排序法： 以一个值为基准值（通常第一个），把数组分成两部分： 前面部分的值都比基准值小， 后面部分的值都比基准值大．
	 * １.每一次递归循环以第一个值为分界点, (1)．先从后面开始检索，值比基准值大就略过，比之小就把此值移到左边指针位置
	 * (2)．再从前面开始检索，值比基准值小就略过，比之大就把此值移到当前的右指针位置
	 * 如果左指针比右指针小，继续循环１和２，直到分界点左边的值较小，右边的之较大, 最后把基准值赋值给当前的左指针位置. 完成一次递归排序,
	 * 数组被分割成两个字子数组 开始递归： 分别对前后两个子数组进行１过程，直至数组被分割成若干个只有一个元素的子数组，整个数组快速排序完成．
	 * 
	 * @param array
	 *            需要排序的子数组(第一次为原数组)
	 * @param startIndex
	 *            开始指针
	 * @param endIndex
	 *            结束指针
	 */
	public static void quickSort(int[] array, int startIndex, int endIndex) {
		if (startIndex >= endIndex) {
			// 递归结束点： 当左右相等或者左大于右时结束函数
			// System.out.println("End same startIndex = " + startIndex + ", endIndex= " +
			// endIndex);
			return;
		}
		int boundary = boundary(array, startIndex, endIndex);
		System.out.println(Arrays.toString(array) + " boundary = " + boundary);
		// 递归排序数组前面部分子数组，先完成前面部分排序
		quickSort(array, startIndex, boundary - 1);

		// 递归后面部分的排序
		quickSort(array, boundary + 1, endIndex);
	}

	/**
	 * 寻找分界点的标记下标 每一次以第一个为基准值，分界点后面部分中较小的值和前面部分中较大的值进行交换
	 * 
	 * @param array
	 * @param startIndex
	 * @param endIndex
	 */
	private static int boundary(int[] array, int startIndex, int endIndex) {
		int standard = array[startIndex];// 取第一个值为基准值
		int leftIndex = startIndex;// 左边界指针
		int rightIndex = endIndex;// 右边界指针

		// 交换leftIndex和rightIndex的值
		while (leftIndex < rightIndex) {
			// 从后面开始往前搜索
			while (leftIndex < rightIndex && array[rightIndex] >= standard) {
				// 当前值比基准值大就略过
				rightIndex--;
			}
			// 发现后面的值比基准值小，把这个值移到最前面的leftIndex位置，rightIndex的位置成为＂空＂
			array[leftIndex] = array[rightIndex];
			//System.out.println(Arrays.toString(array) + " rightIndex = " + rightIndex + ", leftIndex= " + leftIndex);

			// 前面开始检索
			while (leftIndex < rightIndex && array[leftIndex] <= standard) {
				// 当前值比基准值小就略过
				leftIndex++;
			}
			// 发现前面的值比基准值大，就把这个值移到rightIndex位置， leftIndex的位置成为＂空＂
			array[rightIndex] = array[leftIndex];
			//System.out.println(Arrays.toString(array) + " leftIndex = " + leftIndex + ", rightIndex= " + rightIndex);
		}

		// 左边的＂空＂位置赋值为基准值
		array[leftIndex] = standard;
		return leftIndex;
	}
	
	public static void quickSortDescending(int[] array, int startIndex, int endIndex) {
		if (startIndex >= endIndex) {
			// 递归结束点： 当左右相等或者左大于右时结束函数
			// System.out.println("End same startIndex = " + startIndex + ", endIndex= " +
			// endIndex);
			return;
		}
		int boundary = boundaryDescending(array, startIndex, endIndex);
		System.out.println(Arrays.toString(array) + " boundary = " + boundary);
		// 递归排序数组前面部分子数组，先完成前面部分排序
		quickSortDescending(array, startIndex, boundary - 1);

		// 递归后面部分的排序
		quickSortDescending(array, boundary + 1, endIndex);
	}
	
	private static int boundaryDescending(int[] array, int startIndex, int endIndex) {
		int standard = array[startIndex];// 取第一个值为基准值
		int leftIndex = startIndex;// 左边界指针
		int rightIndex = endIndex;// 右边界指针

		// 交换leftIndex和rightIndex的值
		while (leftIndex < rightIndex) {
			// 从后面开始往前搜索
			while (leftIndex < rightIndex && array[rightIndex] <= standard) {
				// 当前值比基准值小就略过
				rightIndex--;
			}
			// 发现后面的值比基准值大，把这个值移到最前面的leftIndex位置，rightIndex的位置成为＂空＂
			array[leftIndex] = array[rightIndex];
			System.out.println(Arrays.toString(array) + " rightIndex = " + rightIndex + ", leftIndex= " + leftIndex);

			// 前面开始检索
			while (leftIndex < rightIndex && array[leftIndex] >= standard) {
				// 当前值比基准值大就略过
				leftIndex++;
			}
			// 发现前面的值比基准值小，就把这个值移到rightIndex位置， leftIndex的位置成为＂空＂
			array[rightIndex] = array[leftIndex];
			System.out.println(Arrays.toString(array) + " leftIndex = " + leftIndex + ", rightIndex= " + rightIndex);
		}

		// 左边的＂空＂位置赋值为基准值
		array[leftIndex] = standard;
		return leftIndex;
	}
}
