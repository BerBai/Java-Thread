package com.ber.thread.synchronize;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ber
 * @version 1.0
 * @description: 安全的
 * @date 21/12/19 16:45
 */
public class SafeList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < 100000; i++) {
            new Thread(() -> {
                synchronized (list) {
                    list.add(Thread.currentThread().getName());
                }
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

/**
 * @author ber
 * @version 1.0
 * @description: 不安全的
 * @date 21/12/19 19:41
 */
class UnsafeList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < 100000; i++) {
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