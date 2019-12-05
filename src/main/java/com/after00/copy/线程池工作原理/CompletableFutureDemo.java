package com.after00.copy.线程池工作原理;

import java.util.concurrent.CompletableFuture;


// 软实力
// 硬实力

// 学习方式

    // 1. 基础，但并不基础，加深基础功底
    // 2. 架构，sso，  curd
    // 3. 学习方法论，源码剖析！
    //    有道无术，术尚可求，有术无道，止于术

// 我们为什么教育
    // 点燃火焰

    // 培训 ； 叫你怎么做，新技术出来，，，，，，
    // ==============================================
    // 教育 ： 授人以鱼不如授人以渔，初心： 利他


// 3天后，SpringBoot源码剖析！
// 单点登陆！
public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {

        //没有返回值的 runAsync 异步调用
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回，update mysql ok");
        });
        completableFuture.get();

        //有返回值的  供给型参数接口
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "completableFuture2");
            int i = 10/0;
            return 1024;
        });

        System.out.println(completableFuture2.whenComplete((t, u) -> { //编译完成，正常结束输出
            System.out.println("===t:" + t);  //正常结果
            System.out.println("===u:" + u);  //信息
        }).exceptionally(e -> {  //结果异常，非正常结束
            System.out.println("=======exception:" + e.getMessage());
            return 555;
        }).get());

    }
}
