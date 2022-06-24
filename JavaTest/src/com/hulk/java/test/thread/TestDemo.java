package com.hulk.java.test.thread;

class BThread extends Thread {
    public BThread() {
        super("[BThread] Thread");
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " start.");
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(threadName + " loop at " + i);
                Thread.sleep(1000);
            }
            System.out.println(threadName + " end.");
        } catch (Exception e) {
            System.out.println("Exception from " + threadName + ".run");
        }
    }
}

class AThread extends Thread {
    BThread bt;

    public AThread(BThread bt) {
        super("[AThread] Thread");
        this.bt = bt;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " start.");
        try {
        	System.out.println(threadName + " join " + bt.getName());
            bt.join();//AThread等待BThread执行完成
            System.out.println(threadName + " end.");
        } catch (Exception e) {
            System.out.println("Exception from " + threadName + ".run");
        }
    }
}

public class TestDemo {
    public static void main(String[] args) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " start.");
        BThread bt = new BThread();
        AThread at = new AThread(bt);
        try {
        	System.out.println(threadName + " start " + bt.getName());
            bt.start();
            System.out.println(threadName + " sleep 2000 millis");
            Thread.sleep(2000);
            System.out.println(threadName + " start " + at.getName());
            at.start();
            System.out.println(threadName + " join " + at.getName());
            at.join();
        } catch (Exception e) {
            System.out.println("Exception from main");
        }
        System.out.println(threadName + " end!");
    }
}