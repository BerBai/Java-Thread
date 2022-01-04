package com.ber.thread.synchronize;

/**
 * @author ber
 * @version 1.0
 * @description: 死锁
 * @date 21/12/19 21:18
 */
public class DeadLock {
    public static void main(String[] args) {
        Borrow b1 = new Borrow(0, "张三");
        Borrow b2 = new Borrow(1, "李四");

        b1.start();
        b2.start();
    }
}

// 书籍1
class Book1 {

}

// 书籍2
class Book2 {

}

// 借书
class Borrow extends Thread {
    // 需要的资源只有一份，这里用static保证资源只有一份
    static Book1 book1 = new Book1();
    static Book2 book2 = new Book2();

    int bookId;
    String userName;

    Borrow(int bookId, String userName) {
        this.bookId = bookId;
        this.userName = userName;
    }

    @Override
    public void run() {
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void makeup() throws InterruptedException {
        if (bookId == 0) {
            // 获得book1的锁
            synchronized (book1) {
                System.out.println(this.userName + "获得book1锁");
                Thread.sleep(1000);
                synchronized (book2) {
                    System.out.println(this.userName + "获得book2锁");
                }
            }
//            synchronized (book2) {
//                System.out.println(this.userName + "获得book2锁");
//            }
        } else {
            // 获得book1的锁
            synchronized (book2) {
                System.out.println(this.userName + "获得book2锁");
                Thread.sleep(2000);
                synchronized (book1) {
                    System.out.println(this.userName + "获得book1锁");
                }
            }
//            synchronized (book1) {
//                System.out.println(this.userName + "获得book1锁");
//            }
        }
    }
}

