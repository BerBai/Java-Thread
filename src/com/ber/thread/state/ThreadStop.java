package com.ber.thread.state;

/**
 * @author ber
 * @version 1.0
 * @description: 线程停止规范：1.线程正常停止，设置次数；2.使用标志位，设置标志位；3.不建议使用stop或destroy等过时或JDK不建议使用的方法
 * @date 21/12/8 10:16
 */
public class ThreadStop implements Runnable {

    // 1.设置线程运行标志位
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("Thread-->" + i++);
        }
    }

    // 2.设置停止线程的方法，转换标志位
    public void stop() {
        this.flag = false;
    }

    public static void main(String[] args) {
        ThreadStop threadStop = new ThreadStop();
        new Thread(threadStop).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("i=" + i);
            if (i == 900) {
                threadStop.stop();
                System.out.println("满足线程停止条件");
            }
        }
    }
}
