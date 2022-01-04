package com.ber.thread.state;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ber
 * @version 1.0
 * @description: sleep() 线程休眠 模拟倒计时
 * @date 21/12/13 19:34
 */
public class ThreadSleep {

    private static void printTime() {
        Date date = new Date(System.currentTimeMillis());
        while (true) {
            try {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
                Thread.sleep(1000);
                date = new Date(System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 3;
        System.out.println("3秒后开始");
        while (true) {
            System.out.println(num--);
            Thread.sleep(1000);
            if (num <= 0) {
                break;
            }
        }
        System.out.println("获取系统时间");
        printTime();
    }
}
