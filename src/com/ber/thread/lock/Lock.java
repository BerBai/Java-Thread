package com.ber.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ber
 * @version 1.0
 * @description: Lock
 * @date 21/12/20 11:49
 */
public class Lock {
    public static void main(String[] args) {
        LockTest lockTest = new LockTest();

        new Thread(lockTest, "张三").start();
        new Thread(lockTest, "李四").start();
        new Thread(lockTest, "王五").start();
    }
}

class LockTest implements Runnable {

    int ticketNums = 10;

    // 定义lock锁
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            // 加锁
            lock.lock();
            try {
                if (ticketNums > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "拿到了第" + ticketNums-- + "张票");
                } else {
                    break;
                }
            } finally {
                // 解锁
                lock.unlock();
            }
        }
    }
}
