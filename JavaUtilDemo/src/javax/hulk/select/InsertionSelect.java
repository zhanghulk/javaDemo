package javax.hulk.select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.hulk.sorts.CollectionsSort;

public class InsertionSelect {
	
	public static void main(String[] args) {
		Integer[] arr = { 3, 8, 4, 7, 9, 2, 12, 5};
		int K = 3;
		int eK = searchKth(Arrays.asList(arr), K);
		System.out.print(eK);
	}
	
	/**
	 * 插入排序思维寻找第K大元素，具体实现逻辑：
	 * 先取出前K个数排序(降序)，再从后面依次插入.
	 * 前面k个已经排序完成的部分为最大的K个元素，第k-1个为第Ｋ大元素.
	 * 构造一个 K 长度的数组，将前K位数复制过来并排序（降序）。
	 * 然后依次将 K+1 到 N 位的数比较并插入 K 长度的数组中。
	 * 返回最后一个即可。
	 * 这时间度为：O( N*K ) 如果 K = N/2  则复杂度为 O( N*N )
	 * @param list
	 * @param k
	 * @return
	 */
	public static int searchKth(List<Integer> list, int k) {
		//找出最大K个元素集合，最后一个为第K大元素
		List<Integer> result = searchKElements(list, k);
		if(result == null || k > result.size()) {
			return -1;
		}
		return result.get(k - 1);//第k - 1个为第K大元素
	}
	
	/**
	 * 插入排序思维寻找最大的K个元素，具体实现逻辑：
	 * 先取出前K个数排序(降序)，再从后面依次插入.
	 * 前面k个已经排序完成的部分为最大的K个元素．
	 * 构造一个 K 长度的数组，将前K位数复制过来并排序（降序）。
	 * 然后依次将 K+1 到 N 位的数比较并插入 K 长度的数组中。
	 * 返回前k个元素的集合。
	 * 这时间度为：O( N*K ) 如果 K = N/2  则复杂度为 O( N*N )
	 * @param list
	 * @param k
	 * @return
	 */
	public static List<Integer> searchKElements(List<Integer> list, int k) {
		if(list == null || k > list.size()) {
			return list;
		}
		//首先取前面的空k个元素为暂时目标
		List<Integer> result = new ArrayList<Integer>(k);
		for (int i = 0; i < k; i++) {
			result.add(list.get(i));
		}

		// 前K位数，按照从大到小排序
		CollectionsSort.sortDescending(result);

		// 后 K+1 位数与前面的有序数组比较，插入适当的位置
		for (int i = k; i < list.size(); i++) {
			int j = k - 1;
			int dest = list.get(i);//第i个目标值
			while (j >= 0 && dest > result.get(j)) {
				j--;// 后面的数比前面已经排序好的数大，说明还要继续往前插入
			}
			// 找到合适的位置
			if (0 <= j && j < k - 1 && dest < result.get(j)) {
				result.add(j + 1, dest);//把目标插入到当前j位置，后面的所有元素会自动后移
				result.remove(result.size() -1);//移除最后一个元素
			} else if (j == -1) { // 结束条件是 j==-1
				result.add(0, dest);//j为－１，说明已经检索到最前了，是当前最大数，放在前面
				result.remove(result.size() -1);//移除最后一个元素
			}
		}
		return result;
	}
}
