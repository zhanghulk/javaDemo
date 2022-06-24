package com.hulk.java.test.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现多线程按照顺序打印log：按需执行 first second third
 * 我们提供了一个类：

public class Foo {
  public void first() { print("first"); }
  public void second() { print("second"); }
  public void third() { print("third"); }
}
三个不同的线程 A、B、C 将会共用一个 Foo 实例。

一个将会调用 first() 方法
一个将会调用 second() 方法
还有一个将会调用 third() 方法
请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 * @author zhanghao
 *
 */
public class PrintTest {
	
	public final static int SLEEP = 500;
	//count未执行计数无实际意义
	int count = 0;
	public void first() {
		sleep(SLEEP, "first");
		count++;
		System.out.println("first: " + count);
	}
	public void second() {
		sleep(SLEEP, "second");
		count++;
		System.out.println("second: " + count);
	}
	public void third() {
		sleep(SLEEP, "third");
		count++;
		System.out.println("third: " + count);
	}
	
	public static void main(String[] args) {
		/**
		 * 共享的打印对象
		 */
		PrintTest mPrintTest = new PrintTest();
		
		/**
		 * 执行状态标记
		 */
		Map<String, Integer> mState = new HashMap<>();
		
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				System.out.println("r1.run: starting");
				mPrintTest.first();
				mState.put("first", 1);//也可写到first()中，状态标记放在那儿都不影响
				System.out.println("r1.run: finished");
			}
		};
		
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				System.out.println("r2.run: starting");
				while(true) {
					//first未执行就循环等待
					if(mState.get("first") != null) {
						break;
					} else {
						sleep(SLEEP, "r2.run");
					}
				}
				mPrintTest.second();
				mState.put("second", 1);
				System.out.println("r2.run: finished");
			}
		};
		
		Runnable r3 = new Runnable() {
			@Override
			public void run() {
				System.out.println("r3.run: starting");
				while(true) {
					//second未执行就循环等待
					if(mState.get("second") != null) {
						break;
					} else {
						sleep(SLEEP, "r3.run");
					}
				}
				mPrintTest.third();
				System.out.println("r3.run: finished");
			}
		};
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);		
		Thread t3 = new Thread(r3);
		//t1  t2  t3 执行顺序可任意， 
		//因为具体什么时候执行不是固定的，是java虚拟机决定线程的执行顺序
		t3.start();
		t1.start();
		t2.start();
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
