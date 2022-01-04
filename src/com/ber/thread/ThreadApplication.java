package com.ber.thread;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.io.FileUtils;
import org.junit.internal.runners.statements.RunAfters;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Stack;
import java.util.concurrent.*;

/**
 * @author ber
 * @version 1.0
 * @description: 并发存在的问题。对同一资源调度时
 * @date 21/12/7 11:11
 */
public class ThreadApplication implements Runnable {

    // 车票数量
    private int ticketNums = 10;

    @Override
    public void run() {
        while (true) {
            if (ticketNums <= 0) {
                break;
            }
            try {
                // 模拟延时
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 购票过程
            System.out.println(Thread.currentThread().getName() + "拿到了第" + ticketNums-- + "票");
        }
    }

    public static void main(String[] args) {
        ThreadApplication application = new ThreadApplication();
        new Thread(application, "小鱼").start();
        new Thread(application, "大熊").start();
        new Thread(application, "小花").start();
    }
}

/**
 * @author ber
 * @version 1.0
 * @description: 多线程模拟龟兔赛跑
 * @date 21/12/7 14:53
 */
class ThreadApplicationRace implements Runnable {
    private static String winner;

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            // 模拟兔子睡觉
            if ("兔子".equals(Thread.currentThread().getName()) && i % 50 == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 判断比赛是否结束
            if (isGameOver(i)) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + "跑了" + +i + "步");
        }
    }

    public boolean isGameOver(int step) {
        if (winner != null) {
            return true;
        }

        if (step >= 100) {
            winner = Thread.currentThread().getName();
            System.out.println("winner is " + winner);
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        ThreadApplicationRace application = new ThreadApplicationRace();
        new Thread(application, "乌龟").start();
        new Thread(application, "兔子").start();
    }
}

/**
 * @author ber
 * @version 1.0
 * @description: 实现多线程同步下载图片 继承Thread实现
 * @date 21/12/7 11:12
 */
class ThreadApplicationMethod1 extends Thread {
    private String url;
    private String fileName;

    public ThreadApplicationMethod1(String url, String fileName) {
        this.fileName = fileName;
        this.url = url;
    }

    @Override
    public void run() {
        WebDownloader downloader = new WebDownloader();
        downloader.downloader(url, fileName);
        System.out.println(fileName + "下载成功");
    }

    public static void main(String[] args) {
        ThreadApplicationMethod1 t1 = new ThreadApplicationMethod1("https://imglf3.lf127.net/img/M0VBOWp1RHZXaUttWXdJc2t2anVEMVpmMGFIalc0VTBOMWphQVluSVVEUEE5S1ZCRm45TnhnPT0.jpg", "1.jpg");
        ThreadApplicationMethod1 t2 = new ThreadApplicationMethod1("https://imglf.lf127.net/img/NVc1cHVseFhyWFcwdHhpdjJydFRvcWJSa0NNbGlRbGN2TXNOS3NwQVdET042YmpmemdjUm5RPT0.jpg", "2.jpg");
        ThreadApplicationMethod1 t3 = new ThreadApplicationMethod1("https://imglf6.lf127.net/img/ZWl4dnBUa0VLMzhXRThRbnV3UWlQelY1dGNPeUdQMUhib2IwOEVvTEhvUTRISnUwNmkwekV3PT0.jpg", "3.jpg");
        t1.start();
        t2.start();
        t3.start();

    }
}

/**
 * @author ber
 * @version 1.0
 * @description: 实现多线程同步下载图片 实现Runnable接口
 * @date 21/12/7 14:06
 */
class ThreadApplicationMethod2 implements Runnable {
    private String url;
    private String fileName;

    public ThreadApplicationMethod2(String url, String fileName) {
        this.fileName = fileName;
        this.url = url;
    }

    @Override
    public void run() {
        WebDownloader downloader = new WebDownloader();
        downloader.downloader(url, fileName);
        System.out.println(fileName + "下载成功");
    }

    public static void main(String[] args) {
        ThreadApplicationMethod2 t1 = new ThreadApplicationMethod2("https://imglf3.lf127.net/img/M0VBOWp1RHZXaUttWXdJc2t2anVEMVpmMGFIalc0VTBOMWphQVluSVVEUEE5S1ZCRm45TnhnPT0.jpg", "1.jpg");
        ThreadApplicationMethod2 t2 = new ThreadApplicationMethod2("https://imglf.lf127.net/img/NVc1cHVseFhyWFcwdHhpdjJydFRvcWJSa0NNbGlRbGN2TXNOS3NwQVdET042YmpmemdjUm5RPT0.jpg", "2.jpg");
        ThreadApplicationMethod2 t3 = new ThreadApplicationMethod2("https://imglf6.lf127.net/img/ZWl4dnBUa0VLMzhXRThRbnV3UWlQelY1dGNPeUdQMUhib2IwOEVvTEhvUTRISnUwNmkwekV3PT0.jpg", "3.jpg");
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();

    }
}

/**
 * @author ber
 * @version 1.0
 * @description: 实现多线程同步下载图片 实现Callable接口
 * @date 21/12/7 15:11
 */
class ThreadApplicationMethod3 implements Callable<Boolean> {
    private String url;
    private String fileName;

    public ThreadApplicationMethod3(String url, String fileName) {
        this.fileName = fileName;
        this.url = url;
    }

    @Override
    public Boolean call() {
        WebDownloader downloader = new WebDownloader();
        downloader.downloader(url, fileName);
        System.out.println(fileName + "下载成功");
        return true;
    }

    public static void main(String[] args) {
        // 1.
        ThreadApplicationMethod3 t1 = new ThreadApplicationMethod3("https://imglf3.lf127.net/img/M0VBOWp1RHZXaUttWXdJc2t2anVEMVpmMGFIalc0VTBOMWphQVluSVVEUEE5S1ZCRm45TnhnPT0.jpg", "1.jpg");
        ThreadApplicationMethod3 t2 = new ThreadApplicationMethod3("https://imglf.lf127.net/img/NVc1cHVseFhyWFcwdHhpdjJydFRvcWJSa0NNbGlRbGN2TXNOS3NwQVdET042YmpmemdjUm5RPT0.jpg", "2.jpg");
        ThreadApplicationMethod3 t3 = new ThreadApplicationMethod3("https://imglf6.lf127.net/img/ZWl4dnBUa0VLMzhXRThRbnV3UWlQelY1dGNPeUdQMUhib2IwOEVvTEhvUTRISnUwNmkwekV3PT0.jpg", "3.jpg");
        // 2.创建执行服务的线程池
        ExecutorService service = Executors.newFixedThreadPool(3);
        // 3.提交执行的线程
        Future<Boolean> res1 = service.submit(t1);
        Future<Boolean> res2 = service.submit(t2);
        Future<Boolean> res3 = service.submit(t3);
        // 4.获取线程执行结果
        try {
            Boolean r1 = res1.get();
            Boolean r2 = res2.get();
            Boolean r3 = res3.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // 5.关闭服务的线程池
        service.shutdown();

    }
}


/**
 * @author ber
 * @version 1.0
 * @description: 网络下载器
 * @date 21/12/7 11:13
 */
class WebDownloader {
    public void downloader(String url, String flineName) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(flineName));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，下载出现问题");
        }
    }
}