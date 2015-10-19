package javax.util.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//arraycopy();
	    int N = 9;
	    List<String>  list = new ArrayList<String>();
	    list.add("aaa");
	    list.add("bbb");
	    list.add("ccc");
	    String[] arr = list.toArray(new String[list.size()]);
	    System.out.println(Arrays.toString(arr));
	}

	
	private static void arraycopy() {
		int[] s = {1,2,3,4,6};
		int N = s.length;
		int[] d = new int[N];
		System.arraycopy(s, 2, d, 3, 2);
		for (int i : d) {
			System.out.println(i);
		}
	}

	private static void testArray() {
		int[][] ss = new int[2][3];
		ss[1][1] = 2;
		System.out.println("length= " + ss.length);
		for (int[] is : ss) {
			for (int i : is) {
				System.out.print(i);
			}
			System.out.println();
		}
	}
}
