package com.ber.thread.state;

/**
 * @author ber
 * @version 1.0
 * @description: 线程礼让，
 * @date 21/12/13 20:06
 */
public class ThreadYield {
    public static void main(String[] args) {
        YieldTest yieldTest = new YieldTest();
        new Thread(yieldTest, "a").start();
        new Thread(yieldTest, "b").start();
    }

}

class YieldTest implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "线程停止");
    }
}