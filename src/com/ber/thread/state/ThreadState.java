package com.ber.thread.state;

/**
 * @author ber
 * @version 1.0
 * @description: 观测测试线程的状态
 * @date 21/12/13 20:25
 */
public class ThreadState {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程结束");
        });

        // 观测状态
        Thread.State state = thread.getState();
        System.out.println(state);

        // 观测线程运行
        thread.start();
        state = thread.getState();
        System.out.println(state);

        while (state != Thread.State.TERMINATED) {
            try {
                Thread.sleep(500);
                state = thread.getState();
                System.out.println(state);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
