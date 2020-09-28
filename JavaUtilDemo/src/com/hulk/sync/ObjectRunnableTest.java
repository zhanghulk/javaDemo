package com.hulk.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 锁实例对象
 * @author hulk
 *
 */
public class ObjectRunnableTest implements Runnable {
	
	/**
	 * 同步方法: 锁实例对象
	 */
	private synchronized  void testSyncMethod() {
        for (int i = 0; i < 10; i++) {
        	try {
        		System.out.println(Thread.currentThread().getId() + " sleeping...");
        		Thread.sleep(1000);        		
        	} catch (Exception e) {
        		System.err.print(e);
        	}
            System.out.println(Thread.currentThread().getId() + "-testSyncMethod:" + i);
        }
    }
 
	@Override
    public void run() {
     testSyncMethod();
    }
 
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        ObjectRunnableTest rt = new ObjectRunnableTest();
        ObjectRunnableTest rt1 = new ObjectRunnableTest();
        exec.execute(rt);
        exec.execute(rt1);
        exec.shutdown();
    }
}
