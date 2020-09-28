package com.hulk.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 使用wait和notify实现线程同步
 * wait、notify以及notifyAll
 * wait、notify、notifyAll是Object对象的属性，并不属于线程。我们先解释这三个的一个很重要的概念

 * wait：使持有该对象的线程把该对象的控制权交出去，然后处于等待状态（注:当调用wait的时候会释放锁并处于等待的状态）
 * notify：通知某个正在等待这个对象的控制权的线程可以继续运行（当前线程先获取锁，使自己的程序开始执行，完成任务后通过notify同样去释放锁，并唤醒正在等待的线程）
 * notifyAll:会通知所有等待这个对象控制权的线程继续运行(和上面一样，只不过是唤醒所有等待的线程继续执行, 如果有多个等待线程,必须执行notifyAll,否则只会有一个waiter收到通知线程继续)
 * 通过wait和notify可以做线程之间的通信，当Notifyer线程处理完毕通知Waiter线程执行, 可以相互通知.
 * @author hulk
 *
 */
public class WaitNotifyTest {

	int count = 0;
	//Object mLock = new Object();
	//mLock可以使用this,效果一样(前提是:wait()/notify()和synchronized()中的对象必须是同一个对象)
	Object mLock = this;
	
	/**
	 * 等待
	 * @throws InterruptedException
	 */
	public void doWait() throws InterruptedException {
		System.out.println(Thread.currentThread() + ">access doWait...");
		synchronized (mLock) {
			System.out.println(Thread.currentThread() + ">doWait: 等待");
			//当前线程等待状态,释放mLock所资源,其他线程的可以进入notifyer中的synchronized代码块
			//如果不执行wait就会知道这个代码块执行完毕才能执行其他线程的synchronized代码块
			mLock.wait();
			//继续执行waiter的逻辑极端
			System.out.println(Thread.currentThread() + ">doWait: go on sum count.");
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread() + "-doWait: " + i);
				TimeUnit.SECONDS.sleep(1);
				count += i;
			}
			System.out.println(Thread.currentThread() + ">doWait: count is " + this.count);
		}
	}

	/**
	 * 通知
	 * @throws InterruptedException
	 */
	public void doNotify() throws InterruptedException {
		System.out.println(Thread.currentThread() + ">access doNotify...");
		synchronized (mLock) {
			TimeUnit.SECONDS.sleep(1);
			System.out.println(Thread.currentThread() + ">doNotify: 准备唤醒");
			//用一个循环,模拟完成一些任务
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread() + "-doNotify:" + i);
				TimeUnit.MILLISECONDS.sleep(500);
				count += i;
			}
			System.out.println("doNotify: ### notify count is " + count);
			//通知Waiter任务已经完成,其他Waiter可以继续执行了.
			//mLock.notify();
			//如果有多个等待线程,必须执行notifyAll,否则只会有一个waiter收到通知线程继续
			mLock.notifyAll();
		}
	}

	/**
	 * 等待线程
	 * @author hulk
	 *
	 */
	public static class Waiter implements Runnable {
		private WaitNotifyTest test;

		public Waiter(WaitNotifyTest test) {
			this.test = test;
		}

		public void run() {
			try {
				System.out.println(Thread.currentThread() + ">Waiter.run...");
				test.doWait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 等待线程2
	 * @author hulk
	 *
	 */
	public static class Waiter2 implements Runnable {
		private WaitNotifyTest test;

		public Waiter2(WaitNotifyTest test) {
			this.test = test;
		}

		public void run() {
			try {
				System.out.println(Thread.currentThread() + ">Waiter2.run...");
				test.doWait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 通知线程
	 * @author hulk
	 *
	 */
	public static class Notifyer implements Runnable {
		private WaitNotifyTest test;

		public Notifyer(WaitNotifyTest test) {
			this.test = test;
		}

		public void run() {
			try {
				System.out.println(Thread.currentThread() + ">Notifyer.run...");
				test.doNotify();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		WaitNotifyTest test = new WaitNotifyTest();
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new Waiter(test));
		//如果有多个等待线程,必须执行notifyAll,否则只会有一个waiter收到通知线程继续
		executorService.execute(new Waiter2(test));
		executorService.execute(new Notifyer(test));
		executorService.shutdown();
	}
}
