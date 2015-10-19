
package javax.util.demo;

public class singletonTest {
    public static void main(String[] args) {
        testSingleton();
    }

    private static void testSingleton() {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        if (s1 == s2/*s1.equals(s2)*/)
            System.out.println("s1 is the same instance with s2");
        else
            System.out.println("s1 is not the same instance with s2");
    }

    public static class Singleton {
        private static Singleton s;

        public static Singleton getInstance() {
            if (s == null)
                s = new Singleton();
            return s;
        }
    }
}
