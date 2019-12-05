package com.after00.copy.线程池工作原理;

import java.util.concurrent.*;

//
public class MyThreadDemo {


    public static void main(String[] args) {

        // Executors 阿里手册不允许！
        // 开启了一个线程池 ， 5个连接
//       ExecutorService threadPool = Executors.newFixedThreadPool(5);   // 5条线程
//        ExecutorService threadPool2 = Executors.newSingleThreadExecutor(); //一条线程
//        ExecutorService threadPool3 = Executors.newCachedThreadPool();

        // CPU  ： cpu核数 + 1
        // IO集中，脚本    cpu核数/1-阻塞系数（0.8~0.9）
        // 压力测试！

        System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService threadPool = new ThreadPoolExecutor(
                15,
                50,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        try {
            for (int i = 1; i <= 9 ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭
            threadPool.shutdown();

        }

    }



}
