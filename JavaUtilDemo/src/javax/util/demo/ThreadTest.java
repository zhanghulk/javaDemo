package javax.util.demo;

public class ThreadTest {

	public static void main(String[] args) {
		MyClass myClzz1 = new MyClass("myClzz1");
		MyClass myClzz2 = new MyClass("myClzz2");
		
		myClzz1.runTest();
		myClzz2.runTest();
	}
	
	public static class MyClass {
		static int j = 5;
		public MyClass(String tag) {
			
		}
		private static void runTest() {
			
			new Thread() {
				@Override
				public void run() {
					synchronized (MyClass.class) {//会锁住全部线程
						for (int i = 0; i < 5; i++) {
				            try {
				               System.out.println(this.getName() + " ==== " + j--);
				               Thread.sleep(100);
				            } catch (InterruptedException e) {
				               e.printStackTrace();
				            }
				         }
					}
				}
			}.start();
		}
	}
	
	public static class MyClass2 {
		private void runTest() {
			new Thread() {
				@Override
				public void run() {
					synchronized (MyClass2.this) {//不会锁住
						for (int i = 0; i < 5; i++) {
				            try {
				               System.out.println(this.getName() + ": " + i);
				               Thread.sleep(100);
				            } catch (InterruptedException e) {
				               e.printStackTrace();
				            }
				         }
					}
				}
			}.start();
		}
	}
}
