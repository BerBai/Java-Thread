package com.ber.thread.lock;

/**
 * @author ber
 * @version 1.0
 * @description: 管程法 实现生产者/消费者模式。
 * 1. 需要四种角色：生产者、消费者、产品、缓冲区
 * 2. 生产者生产产品放到缓冲区，缓冲区如果满了，生产者停止运作，进入等待
 * 3. 消费者从缓冲区拿产品，如果缓冲区产品没有了，先唤醒生产者，然后进入等待
 * @date 21/12/20 12:30
 */
public class ProductorAndConsumer {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();

        new Productor(container).start();
        new Consumer(container).start();
    }
}

// 生产者
class Productor extends Thread {
    SynContainer container;

    public Productor(SynContainer container) {
        this.container = container;
    }

    // 生产
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            container.push(new Product(i));
        }
    }
}

// 消费者
class Consumer extends Thread {
    SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    // 消费
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            container.pop();
        }
    }
}

// 产品
class Product {
    // 产品编号
    int id;

    public Product(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

// 缓冲区
class SynContainer {

    // 容器大小
    Product[] chickens = new Product[10];

    // 容器计数器
    int count = 0;

    // 生产者放入产品
    public synchronized void push(Product product) {
        // 如果容器满了，就需要等待消费者消费
        if (count == chickens.length) {
            //生产者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 如果没有满，我们需要丢入产品
        chickens[count] = product;
        count++;
        System.out.println("生产了第"+product.getId()+"个产品，当前容器容量："+count+"个");
        // 可以通知消费者消费了.
        this.notifyAll();
    }

    // 消费者消费product
    public synchronized Product pop() {
        // 判断能否消费
        if (count == 0) {
            // 消费者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 如果可以消费
        count--;
        Product product = chickens[count];

        System.out.println("消费了-->" + product.getId() + "产品，容器剩余："+count);

        // 缓存区消费完了，通知生产者生产
        this.notifyAll();
        return product;
    }
}