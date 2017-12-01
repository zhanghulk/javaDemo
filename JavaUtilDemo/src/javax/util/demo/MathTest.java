package javax.util.demo;

public class MathTest {

    public static void main(String[] args) {
    	//testNum();
    	double d = 0.000;
        System.out.println(d==0);
    }
    
    private static void testNum() {
    	System.out.println(Math.pow(3, 4));//次方Math.pow(m, n), m的n次方
    	int day = 9;
        int day2 = day^4;//异或
        System.out.println(day2);
	}
}
