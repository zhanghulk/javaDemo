package javax.hulk.select;

import java.util.Arrays;

public class BinarySelect {

	public static void main(String[] args) {
		int[] arr = { 13, 18, 14, 7, 29, 23, 12, 29, 65};
		int K = 2;
		int Kth = searchKth(arr, K);
		System.out.print(Arrays.toString(arr) + ", The " + K + "th= " + Kth);
	}
	
	/**
	 * 二分搜索法思维
	 * 假设N个数的最大值vMax, 最小元素vMin, 第K大数值为p，则p必然处于区间[vMin, vMax]范围之间．
	 * 此问题就演变为：　在这个区间内使用二分搜索出p：
	 * 取vMin和vMax的中间值为vMid，[vMin, vMax]中二分搜索大于等于中间值的元素个数. 
	 * 如果大于等于K，说明p位于[vMid, vMax]区间(vMin=vMid)，反之位于[vMin, vMid]区间(vMax=vMid);
	 * 逐步缩小区间[vMin, vMax]，当区间仅包含一个元素(或者多个相等的元素)，这个元素就是第K大元素．
	 * 此算法的缺陷：控制循环的delta取值比数组N个数中任意两个不相等的元素的差值还要小．整型数组通常取0.5
	 * @param arr
	 * @param K
	 * @return
	 */
	public static int searchKth(int[] arr, int K) {
		int vMax = searchMax(arr);
		int vMin = searchMin(arr);
		int vMid = 0;
		float delta = 0.5f;// 整型数组，delta取0.5最小差值一半
		int N = arr.length;
		int biggerCount = 0;
		while (vMax - vMin > delta) {
			vMid = vMin + (vMax - vMin) / 2;
			System.out.println("max= " + vMax + ", min= " + vMin + ", mid= " + vMid
					+ ", biggerCount= " + biggerCount + ", K= " + K);
			if(vMid == vMin || vMid == vMax) {
				//vMid和vMin相等，找到
				return vMin;
			}
			biggerCount = searchBiggerCount(arr, N, vMid);
			if (biggerCount >= K) {
				vMin = vMid;
			} else {
				vMax = vMid;
			}
		}
		return vMid;
	}

	public static int searchBiggerCount(int[] arr, int N, int midE) {
		if (arr.length == 0) {
			return 0;
		}
		int biggerCount = 0;
		for (int i = 0; i < N; i++) {
			if (arr[i] >= midE) {
				biggerCount++;
			}
		}
		return biggerCount;
	}

	public static int searchMax(int[] arr) {
		if (arr.length == 0) {
			return 0;
		}
		int key = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > key) {
				key = arr[i];
			}
		}
		return key;
	}

	public static int searchMin(int[] arr) {
		if (arr.length == 0) {
			return 0;
		}
		int key = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < key) {
				key = arr[i];
			}
		}
		return key;
	}
}
