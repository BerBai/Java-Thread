package com.ber.lambda;

/**
 * @author ber
 * @version 1.0
 * @description: lambda表达式推导
 * @date 21/12/7 16:30
 */
public class LambdaExpression {
    // 3.静态内部类
    static class LambdaImpl2 implements LambdaInterface {

        @Override
        public void lambda() {
            System.out.println("LambdaInterface实现类-静态内部类");
        }
    }

    public static void main(String[] args) {
        LambdaInterface lambda = new LambdaImpl();
        lambda.lambda();


        lambda = new LambdaImpl2();
        lambda.lambda();

        // 4.局部内部类
        class LambdaImpl3 implements LambdaInterface {

            @Override
            public void lambda() {
                System.out.println("LambdaInterface实现类-局部内部类");
            }
        }

        lambda = new LambdaImpl3();
        lambda.lambda();

        //  5.匿名内部类
        lambda = new LambdaInterface() {
            @Override
            public void lambda() {
                System.out.println("LambdaInterface实现类-匿名内部类");
            }
        };
        lambda.lambda();

        // 6.lambda表达式简化
        lambda = () -> {
            System.out.println("LambdaInterface实现类-lambda方式实现");
        };
        lambda.lambda();
    }
}

// 1.定义函数式接口
interface LambdaInterface {
    void lambda();
}

// 2.实现类
class LambdaImpl implements LambdaInterface {

    @Override
    public void lambda() {
        System.out.println("LambdaInterface实现类");
    }
}