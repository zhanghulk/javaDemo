package javax.hulk.select;

import java.util.Arrays;

/**
 * 分割查找法查找第K大数：
 * 题目：给定一个无序序列和一个数k，求这个无序序列中第K大的数
分析：
方案一：最朴素的方法利用快速排序然后找到第k个数，时间复杂度O(nlogn)
方案二：根据快速排序中的partition方法，每次可以把一个序列分成两个部分，左边全部小于等于基准，右边的全部大于等于基准。如果partition方法中基准的位置刚好是第k个则可以直接得多这个数，如果大于k说明在左边，如果小于k说明在右边。
              利用这个方法每次可以将序列不断的划分直至找到这第k个数， 因为partition函数的时间复杂度最大为O(n)，而我们每次都可以把序列分成一半，因此总的时间复杂度还是O(n)。
              时间复杂度的证明：
              1. 假设总的元素个数有n个，则
                  第一次枚举n个数
                  第二次枚举n/2
                  第三次枚举n/4
                  ...................1
              2. 则总的时间为n+n/2+n/4+....+1 <= 2n，因此时间复杂度为O(n)
举例：
例如无序序列为0 9 -1 6 7 3 5，k为3则第k大的数为6。
 * @author hulk
 *
 */
public class PartitionSelect {

	public static void main(String[] args) {
		int arrNum[] = { 0, 9, -1, 6, 7, 3, 5 };
		System.out.println(Arrays.toString(arrNum));
		PartitionSelect sel = new PartitionSelect();
		int K = 4;
		int Kth = sel.selectKth(arrNum, arrNum.length, K);
		System.out.println(Arrays.toString(arrNum) + ", The " + K + "th = " + Kth);
	}
	
	/**
	 * 分割思维求第k大的数
	 * @param arrNum
	 * @param n
	 * @param k
	 * @return
	 */
	public int selectKth(int[] arrNum, int n, int k) {
		// 输出-1表示不合法
		if (arrNum == null || n <= 0 || k <= 0 || k > n) {
			return -1;
		}
		// 第k大的数等价于找第n-k+1小的数
		k = n - k + 1;
		int l = 0;
		int r = n - 1;
		while (l <= r) {//r > l结束循环
			int index = partition(arrNum, l, r);
			if (index + 1 == k) { // 找到
				return arrNum[index];
			} else if (index + 1 < k) { // 在右边序列找
				l = index + 1;
			} else { // 在左边序列找
				r = index - 1;
			}
		}
		return -1;//未找到
	}

	/**
	 *  分割函数方法
	 * @param arrNum
	 * @param l
	 * @param r
	 * @return
	 */
	public int partition(int[] arrNum, int l, int r) {
		// 不合法数据
		if (arrNum == null || l > r) {
			return -1;
		}
		int mid = (l + r) >> 1;//取中间指针: (l + r) / 2
		System.out.println("partition: " +  Arrays.toString(arrNum) + ", l= " + l + ", r= " + r + ", mid= " + mid);
		swap(arrNum, mid, r);//交换中间和右边的值,后面要以最右边个作为基准值
		int leftSeqIndex = l;
		// 枚举区间: 由左边开始
		for (int i = l; i < r; i++) {
			if (arrNum[r] < arrNum[i]) {//当前位置数小于右边个值(即中间个值)
				if (i > leftSeqIndex) {//当前指针大于左边指针，交换
					System.out.println("partition swap: i= " + i + ", leftSeqIndex= " + leftSeqIndex);
					swap(arrNum, i, leftSeqIndex);
				}
				++leftSeqIndex;//leftSeqIndex向右移动，落后i一个位置
			}
		}
		// 基准交换回来原来的
		swap(arrNum, leftSeqIndex, r);
		System.out.println("partition: " + Arrays.toString(arrNum) + ", leftSeqIndex= " + leftSeqIndex);
		return leftSeqIndex;
	}

	/**
	 * 交换指针值
	 * @param arrNum
	 * @param i
	 * @param j
	 */
	private void swap(int[] arrNum, int i, int j) {
		int temp = arrNum[i];
		arrNum[i] = arrNum[j];
		arrNum[j] = temp;
	}
}
