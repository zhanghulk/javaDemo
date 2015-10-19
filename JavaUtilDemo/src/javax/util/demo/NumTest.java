package javax.util.demo;

public class NumTest {
    public static final int FLAG_SMART_FORWARD = 1<<0;
    public static final int FLAG_SMART_FORWARD_1 = 1<<1;
    public static final int FLAG_SMART_FORWARD_2 = 1<<2;
    public static final int FLAG_SMART_FORWARD_3 = 1<<3;
    public static final int FLAG_SMART_FORWARD_4 = 1<<4;
    public static int mFlags = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println(gcd(20, 15));
	    mFlags |= FLAG_SMART_FORWARD_3;
	    mFlags |= FLAG_SMART_FORWARD_4;
	    System.out.println((mFlags & FLAG_SMART_FORWARD_3) != 0);
	    mFlags &= ~FLAG_SMART_FORWARD_3;
        System.out.println((mFlags & FLAG_SMART_FORWARD_3) != 0);
        System.out.println((mFlags & FLAG_SMART_FORWARD_4) != 0);
        mFlags |= FLAG_SMART_FORWARD_3;
        System.out.println((mFlags & FLAG_SMART_FORWARD_3) != 0);
	}

	static int gcd(int a, int b) {
		int r;
		while (b > 0) {
			r = a % b;
			a = b;
			b = r;
		}
		return a;
	}
}
