package javax.util.demo;

public class CharsetTest {

	public static void main(String[] args) {
		testR();
		testN();
	}
	
	private static void testN() {
		String s = "'sds gdasda" + "\n" + "edaeafd'";
		System.out.println("转换前：" + s);

		s = s.replaceAll("\r", "");

		System.out.println("转换后：" + s);
	}
	
	private static void testR() {
		String s = "'sds gdasda" + "\r" + "edaeafd'";
		System.out.println("转换前：" + s);

		s = s.replaceAll("\r", "");

		System.out.println("转换后：" + s);
	}
}
