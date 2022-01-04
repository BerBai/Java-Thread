package com.ber.thread;

import java.util.concurrent.*;

/**
 * @author ber
 * @version 1.0
 * @date 21/12/7 10:58
 */
public class CreateThread {

}

/**
 * @author ber
 * @version 1.0
 * @description: 创建线程方式一：继承Thread类；重写run()方法；调用start开启线程
 * @date 21/12/7 11:00
 */
class CreateThreadMethod1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("run线程" + i);
        }
    }

    // 线程开启不一定立即执行，由CPU调度
    public static void main(String[] args) {
        // 1.创建线程对象
        CreateThreadMethod1 threadMethod1 = new CreateThreadMethod1();
        // 2.调用start()开启线程
        threadMethod1.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("main主线程" + i);
        }
    }
}

/**
 * @author ber
 * @version 1.0
 * @description: 创建线程方式二：实现Runnable接口；重写run()方法；创建run()方法的Thread对象；调用Thread类对象的start()方法开启线程
 * @date 21/12/7 13:56
 */
class CreateThreadMethod2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("run线程" + i);
        }
    }

    public static void main(String[] args) {
        // 1.创建Runnable接口实现类对象
        CreateThreadMethod2 threadMethod2 = new CreateThreadMethod2();
//        // 2.创建线程对象，通过线程对象来开启线程
//        Thread thread = new Thread(threadMethod2);
//        // 3.调用start()开启线程
//        thread.start();

        // 2-3合并
        new Thread(threadMethod2).start();


        for (int i = 0; i < 1000; i++) {
            System.out.println("main主线程" + i);
        }
    }
}

/**
 * @author ber
 * @version 1.0
 * @description: 创建线程方式三：实现Callable接口；重写call方法；创建call方法的Thread对象；创建执行服务的线程池；提交需要执行的线程；获取线程的执行结构；关闭线程池
 * @date 21/12/7 14:56
 */
class CreateThreadMethod3 implements Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println("run线程" + i);
        }
        return true;
    }

    public static void main(String[] args) {
        // 1.创建Callable接口实现类对象
        CreateThreadMethod3 threadMethod3 = new CreateThreadMethod3();
        // 2.创建执行服务的线程池
        ExecutorService service = Executors.newFixedThreadPool(1);
        // 3.提交执行的线程
        Future<Boolean> result = service.submit(threadMethod3);
        // 4.获取线程的结果
        try {
            Boolean aBoolean = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // 5.关闭服务的线程池
        service.shutdown();


        for (int i = 0; i < 1000; i++) {
            System.out.println("main主线程" + i);
        }
    }
}
