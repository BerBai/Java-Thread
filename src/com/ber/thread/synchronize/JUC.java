package com.ber.thread.synchronize;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author ber
 * @version 1.0
 * @description: JUC安全类型的集合
 * @date 21/12/19 20:28
 */
public class JUC {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName());
            }).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
