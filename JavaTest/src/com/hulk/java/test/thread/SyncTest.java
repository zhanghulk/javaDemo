package com.hulk.java.test.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class SyncTest {
	
	public final static int SLEEP = 500;
	
	int count = 0;
	
	byte[] mLock = new byte[0];
	
	/**
	 * 同步方法被调用时，一个童虎函数被执行，其他同步方法也会被当前线程锁住，均不会被其他线程执行
	 */
	public synchronized void first() {
		sleep(SLEEP, "first");
		count++;
		System.out.println("first: " + count);
	}
	public synchronized void second() {
		sleep(SLEEP, "second");
		count++;
		System.out.println("second: " + count);
	}
	public void third() {
		synchronized(mLock) {
			sleep(SLEEP, "third");
			count++;
			System.out.println("third: " + count);
		}
	}
	
	public static void main(String[] args) {
		SyncTest test = new SyncTest();
		
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				System.out.println("r1.run: starting");
				test.first();
				System.out.println("r1.run: finished");
			}
		};
		
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				System.out.println("r2.run: starting");
				test.second();
				System.out.println("r2.run: finished");
			}
		};
		
		Runnable r3 = new Runnable() {
			@Override
			public void run() {
				System.out.println("r3.run: starting");
				test.third();
				System.out.println("r3.run: finished");
			}
		};
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);		
		Thread t3 = new Thread(r3);
		t1.start();
		t2.start();
		t3.start();
		
		//LinkedBlockingQueue queue = new LinkedBlockingQueue();
		//queue.add(arg0);
	}

	private static void sleep(long millis, String remark) {
		try {
			//System.out.println("Start  sleep: " + millis + ", remark=" +remark);
			Thread.sleep(millis);
			//System.out.println("Finish sleep: " + millis + ", remark=" +remark);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
