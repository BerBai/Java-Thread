package com.ber.lambda;

/**
 * @author ber
 * @version 1.0
 * @description: 带参数的函数式接口的Lambda推导
 * lambda表达式可直接省略参数类型，多参数时需要统一去掉参数，加()；在单参数情况下，可省略()；在单语句情况下，可省略{}；
 * @date 21/12/7 16:53
 */
public class LambdaExpressionParamete {
    // 3.静态内部类
    static class LambdaImplPar2 implements LambdaInterfacePar {

        @Override
        public void lambda(String name) {
            System.out.println(name + " LambdaInterfacePar实现类-静态内部类");
        }
    }

    public static void main(String[] args) {
        LambdaInterfacePar lambda = new LambdaImplPar();
        lambda.lambda("Ber");


        lambda = new LambdaImplPar2();
        lambda.lambda("Ber");

        // 4.局部内部类
        class LambdaImplPar3 implements LambdaInterfacePar {

            @Override
            public void lambda(String name) {
                System.out.println(name + " LambdaInterfacePar实现类-局部内部类");
            }
        }

        lambda = new LambdaImplPar3();
        lambda.lambda("Ber");

        //  5.匿名内部类
        lambda = new LambdaInterfacePar() {
            @Override
            public void lambda(String name) {
                System.out.println(name + " LambdaInterfacePar实现类-匿名内部类");
            }
        };
        lambda.lambda("Ber");

        // 6.lambda表达式简化
        lambda = (String name) -> {
            System.out.println(name + " LambdaInterfacePar实现类-lambda方式实现");
        };
        lambda.lambda("Ber");

        // 7.lambda表达式 简化参数类型
        lambda = (name) -> {
            System.out.println(name + " LambdaInterfacePar实现类-lambda方式实现，简化参数类型");
        };
        lambda.lambda("Ber");

        // 8.lambda表达式 简化括号（单参数适用）
        lambda = name -> {
            System.out.println(name + " LambdaInterfacePar实现类-lambda方式实现，简化括号");
        };
        lambda.lambda("Ber");

        // 9.lambda表达式 简化大括号（单语句适用）
        lambda = name -> System.out.println(name + " LambdaInterfacePar实现类-lambda方式实现，简化大括号");
        lambda.lambda("Ber");
    }
}

// 1.定义函数式接口 参数
interface LambdaInterfacePar {
    void lambda(String name);
}

// 2.实现类
class LambdaImplPar implements LambdaInterfacePar {

    @Override
    public void lambda(String name) {
        System.out.println(name + " LambdaInterfacePar实现类");
    }
}