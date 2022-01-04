package com.ber.thread.state;

/**
 * @author ber
 * @version 1.0
 * @description: 线程的优先级
 * @date 21/12/13 20:40
 */
public class ThreadPriority {

    // 主线程默认优先级
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "-->" + Thread.currentThread().getPriority());
        PriorityTest priorityTest = new PriorityTest();
        Thread a1 = new Thread(priorityTest, "a1");
        Thread a2 = new Thread(priorityTest, "a2");
        Thread a3 = new Thread(priorityTest, "a3");
        Thread a4 = new Thread(priorityTest, "a4");
        Thread a5 = new Thread(priorityTest, "a5");
        Thread a6 = new Thread(priorityTest, "a6");

        a1.start();
        a2.setPriority(Thread.MIN_PRIORITY);
        a2.start();
        a3.setPriority(Thread.NORM_PRIORITY);
        a3.start();
        a4.setPriority(4);
        a4.start();
        a5.setPriority(9);
        a5.start();
        a6.setPriority(Thread.MAX_PRIORITY);
        a6.start();
    }
}

class PriorityTest implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "-->" + Thread.currentThread().getPriority());
    }
}
