package javax.hulk.sorts;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionsSort {

	/**
	 * 按照从小到大排序
	 * @param list
	 * @return
	 */
	public static void sortAscending(List<Integer> list) {
		Collections.sort(list, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2) {
					return 1;
				}
				if (o1 < o2) {
					return -1;
				}
				return 0;
			}
		});
	}
	
	/**
	 * 按照从大到小排序
	 * @param list
	 * @return
	 */
	public static void sortDescending(List<Integer> list) {
		Collections.sort(list, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2) {
					return -1;
				}
				if (o1 < o2) {
					return 1;
				}
				return 0;
			}
		});
	}
}
