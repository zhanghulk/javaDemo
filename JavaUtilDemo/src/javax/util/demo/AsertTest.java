package javax.util.demo;

public class AsertTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean isAssert = true;
		//断言1结果为true，则继续往下执行
        assert isAssert : "断言1没有问题 Not go ";
        System.out.println("断言1没有问题，Go！");
 
        System.out.println("\n-----------------\n");
        isAssert = !isAssert;
        //断言2结果为false,程序终止
        assert isAssert : "断言失败，此表达式的信息将会在抛出异常的时候输出！";
        System.out.println("断言2没有问题，Go！");
	}

}
