package com.ber.proxy;

/**
 * @author ber
 * @version 1.0
 * @description: 静态代理模式，真实对象与代理对象都要实现同一接口，代理角色要代理真实角色
 * 实例：婚庆公司模式
 * 优势：代理角色可以做其他真实角色做不了的事情，而真实角色只需要专注做自己的事情
 * @date 21/12/7 15:28
 */
public class StaticProxy {
    public static void main(String[] args) {
        // 传统模式
        You you = new You();
        you.marry();

//        // 静态代理模式
//        WeddingCompany weddingCompany = new WeddingCompany(new You());
//        weddingCompany.marry();
        // 简化为一行
        new WeddingCompany(new You()).marry();

//        //  线程的静态代理模式
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("love you");
//            }
//        }).start();
        //  线程的静态代理模式，简化——使用Lamda表达式
        new Thread(() -> System.out.println("love you")).start();
    }
}

interface Marry {
    void marry();
}

/**
 * @author ber
 * @version 1.0
 * @description: 真实角色
 * @date 21/12/7 15:48
 */
class You implements Marry {

    @Override
    public void marry() {
        System.out.println("你结婚啦");
    }
}

/**
 * @author ber
 * @version 1.0
 * @description: 代理角色
 * @date 21/12/7 15:48
 */
class WeddingCompany implements Marry {
    private Marry target;

    WeddingCompany(Marry target) {
        this.target = target;
    }


    @Override
    public void marry() {
        before();
        this.target.marry();
        after();
    }

    private void before() {
        System.out.println("婚礼之前，布置婚礼现场");
    }

    private void after() {
        System.out.println("婚礼之后，结算尾款");
    }

}