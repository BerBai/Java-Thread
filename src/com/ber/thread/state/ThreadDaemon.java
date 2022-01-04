package com.ber.thread.state;

/**
 * @author ber
 * @version 1.0
 * @description: 守护线程
 * @date 21/12/19 14:28
 */
public class ThreadDaemon {

    public static void main(String[] args) {
        Thread userThread = new Thread(new User());
        Thread daemonThread = new Thread(new Daemon());
        // 设置守护线程
        daemonThread.setDaemon(true);
        userThread.start();
        daemonThread.start();
    }
}

class User implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i < 100000; i++) {
            System.out.println("用户线程执行。。。");
        }
        System.out.println("用户线程执行结束！！");
    }
}

class Daemon implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("守护线程执行。。。");
        }
    }
}
