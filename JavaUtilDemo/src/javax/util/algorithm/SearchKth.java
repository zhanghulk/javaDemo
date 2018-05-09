package javax.util.algorithm;

public class SearchKth {

	public static void main(String[] args) {
		int[] arr = {3, 8, 4, 7, 9, 12};
		int K = 2;
		int eK = searchKth(arr, K);
		System.out.print(eK);
	}
	
	public static int searchKth(int[] arr, int K) {
		int max = searchMax(arr);
		int min = searchMin(arr);
		float delta = 0.5f;//整型数组，delta取0.5最小差值一半
		int N = arr.length;
		int mid = 0;
		System.out.println("max= " + max + ", min= " + min + ", mid= " + mid);
		while(max - min > delta) {
			mid = (int) (min + (max - min) * 0.5);
			System.out.println("max= " + max + ", min= " + min + ", mid= " + mid);
			if(searchBiggerCount(arr, N, mid) >= K) {
				min = mid;
			} else {
				max = mid;
			}
		}
		return mid;
	}
	
	public static int searchBiggerCount(int[] arr, int N, int mid) {
		if(arr.length == 0) {
			return 0;
		}
		int biggerCount = 0;
		for(int i = 0; i < N; i++) {
			if(arr[i] >= mid) {
				biggerCount++;
			}
		}
		return biggerCount;
	}
	
	public static int searchMax(int[] arr) {
		if(arr.length == 0) {
			return 0;
		}
		int key = arr[0];
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] > key) {
				key = arr[i];
			}
		}
		return key;
	}
	
	public static int searchMin(int[] arr) {
		if(arr.length == 0) {
			return 0;
		}
		int key = arr[0];
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] < key) {
				key = arr[i];
			}
		}
		return key;
	}
}
