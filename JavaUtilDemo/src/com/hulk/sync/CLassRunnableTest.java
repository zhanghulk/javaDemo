package com.hulk.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 锁类对象
 * @author hulk
 *
 */
public class CLassRunnableTest implements Runnable {

	/**
	 * 同步方法: 锁类对象
	 */
	private void testSyncBlock() {
        synchronized (CLassRunnableTest.class) {
            for (int i = 0; i < 10; i++) {
            	
            	try {
            		System.out.println(Thread.currentThread().getId() + " sleeping...");
            		Thread.sleep(1000);        		
            	} catch (Exception e) {
            		System.err.print(e);
            	}
            	
                System.out.println(Thread.currentThread().getId()+"-testSyncBlock:" + i);
            }
        }
    }
	
	@Override
	public void run() {
		testSyncBlock();
	}

	public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        CLassRunnableTest rt = new CLassRunnableTest();
        CLassRunnableTest rt1 = new CLassRunnableTest();
        exec.execute(rt);
        exec.execute(rt1);
        exec.shutdown();
    }
}
