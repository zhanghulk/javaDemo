package javax.util.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    static Lock mLock = new ReentrantLock();
    static boolean canExe = false;
    static int count = 0;
    public static void main(String[] args) {
        System.out.println("lock  javax.util.demo ");
        
        System.out.println("locking ................... ");
        ////////////////////////waiting/////////////////////////////
        if(!bindF()) {
            mLock.lock();
        }
        System.out.println("unlocked >>>>>>>>>>>>>>>>>>> count= " + count);
    }
    
    private static boolean bindF() {
        System.out.println(" printF to get lock ----------------- ");
        if(count == 0) {
            bind();
            return false;
        } else {
            return true;
        }
        
    }

    private static void bind() {
        new Thread(new Runnable() {
            public void run() {
                mLock.lock();
                waitF();
            }
        }).start();
    }
    private static void waitF() {
        try {
            count++;
            System.out.println(" printF sleeping .............4 s.......count= " + count);
            Thread.sleep(4000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
        } finally {
            System.out.println("printF to unlock ----------------- ");
            mLock.unlock();
            System.out.println("unlocked ----------------- ");
        }
    }
}
