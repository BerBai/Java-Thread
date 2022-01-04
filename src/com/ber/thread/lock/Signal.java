package com.ber.thread.lock;

/**
 * @author ber
 * @version 1.0
 * @description: 信号灯法 即生产即消费
 * @date 22/1/4 14:59
 */
public class Signal {
    public static void main(String[] args) {
        Market market = new Market();
        new ProductorS(market).start();
        new ConsumerS(market).start();
    }
}

// 生产者
class ProductorS extends Thread {
    Market market;
    public ProductorS(Market market) {
        this.market = market;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++) {
            market.in(i);
        }
    }
}

// 消费者
class ConsumerS extends Thread {
    Market market;
    public ConsumerS(Market market) {
        this.market = market;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++) {
            market.out();
        }
    }
}

//
class Market {
    // 有货标志位
    // 有：T，生产者等待
    // 无：F，消费者等待
    boolean flag = false;

    int id;

    // 生产
    public synchronized void in(int id) {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e ) {
                e.printStackTrace();
            }
        }

        this.id = id;
        this.flag = !this.flag;
        this.notifyAll();
        System.out.println("生产者生产 " + id);
    }

    public synchronized void out() {
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e ) {
                e.printStackTrace();
            }
        }
        this.flag = !this.flag;
        this.notifyAll();
        System.out.println("消费者消费 "+this.id);
    }
}