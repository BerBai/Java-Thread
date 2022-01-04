package com.ber.thread.synchronize;

/**
 * @author ber
 * @version 1.0
 * @description: 加锁的并发
 * @date 21/12/19 16:10
 */
public class SafeSample {
    public static void main(String[] args) {
        SafeBuy station = new SafeBuy();
        new Thread(station, "张三").start();
        new Thread(station, "李四").start();
        new Thread(station, "王五").start();
    }
}


class SafeBuy implements Runnable {

    // 票数
    private int ticketNums = 10;
    // 线程停止标签
    boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void buy() throws InterruptedException {
        if (ticketNums <= 0) {
            flag = false;
            return;
        }
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + "买到了第" + ticketNums-- + "张票");
    }
}
