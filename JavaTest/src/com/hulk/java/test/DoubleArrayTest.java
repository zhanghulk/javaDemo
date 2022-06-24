package com.hulk.java.test;

/**
 * 二维数组遍历
 * 二维数组遍历耗时测试数量：5000
 * 先行后列耗时1：46801759
 * 先列后行耗时2：389399550
 * 二维数组遍历耗时测试数量：50
 * 先行后列耗时1：9087
 * 先列后行耗时2：9482
 * @author zhanghao
 *
 */
public class DoubleArrayTest {

	public static final int COUNT = 5000; 
	public static void main(String[] args) {
		arrayTraversal(COUNT);
		
		arrayTraversal(50);
	}

	/**
	  * 二维数组遍历方式（先行后列、先列后行）差异测试
	  * eg1:
	  * 二维数组遍历耗时测试数量：5000
	  * 先行后列耗时1：46801759
	  * 先列后行耗时2：389399550
	  * eg2:
	  * 二维数组遍历耗时测试数量：50
	  * 先行后列耗时1：9087
	  * 先列后行耗时2：9482
	  * 结论： 两种方式遍历复杂度一样，数量不同，导致耗时差异非常大；
	  * 原因：1. 数据较多时，内存不是一次性把全部数据加载到缓存，有的数据是在内存中，没有加载到缓存，获取比较耗时；
	  *     2. 先列后行存在“翻页”现象：系统通常是按照行把对象构造出来，先列就需要取做更多寻址，需要的对象不在缓存，需要重新构造，所以比较慢。
	  * 可以看出耗时相差很大。相同的时间复杂度，但是执行的时间相差这么大。
	  * 问题在操作系统和缓存大小限制。有的操作系统在进行数据缓存的时候是将二维数组的整行进行存储，如果按列进行读取则会出现缓存丢失，会去内存中读。
	  * 当然也可能是只会缓存当前位置的同行的后一个数据。具体看操作系统的实现，这两种都是加速大概率事件的思想（PC存储下一个指令也是这个思想）。
	  * 直接从缓存中读取数据的速度肯定是比去内存中读取的要快，这就是两者差异这么大的原因。
	  * 当然如果内存足够大，能直接缓存所有的二维数组的数据，就不会又这种差异
	 * @param loopCount
	 */
	public static void arrayTraversal(int count) {
		System.out.println("二维数组遍历耗时测试数量：" + count);
		int x = count;
		int y = count;
        int[][] array = new int[x][y];
        long startTime = System.nanoTime();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                array[i][j]+=array[i][j];
            }
        }
        long endTime = System.nanoTime();
        System.out.println("先行后列耗时1：" + (endTime - startTime));
        
        //先列后行
        long startTime2 = System.nanoTime();
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                array[j][i]+=array[j][i];
            }
        }
        long endTime2 = System.nanoTime();
        System.out.println("先列后行耗时2：" + (endTime2 - startTime2));
	}
}
