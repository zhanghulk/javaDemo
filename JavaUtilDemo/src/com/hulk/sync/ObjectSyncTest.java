package com.hulk.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObjectSyncTest {
	public static class A {
		private Object obj = "a";

		public void a() {
			synchronized (obj) {
				for (int i = 0; i < 10; i++) {
					try {
	            		System.out.println(Thread.currentThread().getId() + " sleeping...");
	            		Thread.sleep(1000);        		
	            	} catch (Exception e) {
	            		System.err.print(e);
	            	}
					System.out.println(Thread.currentThread() + ".a:" + i);
				}
			}
		}

		public void b() {
			for (int i = 0; i < 10; i++) {
				try {
            		System.out.println(Thread.currentThread().getId() + " sleeping...");
            		Thread.sleep(1000);        		
            	} catch (Exception e) {
            		System.err.print(e);
            	}
				System.out.println(Thread.currentThread() + ".b:" + i);
			}

		}
	}

	public static class B implements Runnable {
		private A a;

		public B(A a) {
			this.a = a;
		}

		public void run() {
			a.b();
		}
	}

	public static class C implements Runnable {
		private A a;

		public C(A a) {
			this.a = a;
		}

		public void run() {
			a.a();
		}
	}

	public static class E implements Runnable {
		private A a;

		public E(A a) {
			this.a = a;
		}

		public void run() {
			a.a();
		}
	}

	public static void main(String[] args) {
		A a = new A();
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new E((a)));
		executorService.execute(new B(a));
		executorService.execute(new C(a));
		executorService.shutdown();
	}

}
