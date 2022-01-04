package com.ber.thread.state;

/**
 * @author ber
 * @version 1.0
 * @description: join
 * @date 21/12/13 20:17
 */
public class ThreadJoin implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("join --> " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadJoin threadJoin = new ThreadJoin();
        Thread thread = new Thread(threadJoin);
        thread.start();

        for (int i = 0; i < 1000; i++) {
            if (i == 200) {
                thread.join();
            }
            System.out.println("main --> " + i);
        }
    }
}
