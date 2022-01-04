package com.ber.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ber
 * @version 1.0
 * @description: 线程池
 * @date 22/1/4 17:02
 */
public class ThreadPool {
    public static void main(String[] args) {
        // 1.创建固定大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new MyThread(i));
        }
        // 2.关闭线程池
        executorService.shutdown();
    }
}

class MyThread implements Runnable {
    private int id;

    MyThread(int id){
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}