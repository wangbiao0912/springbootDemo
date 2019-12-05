package com.after00.copy.JMM;

public class JMMDemo {

    //volatile，他也不能保证原子性！
        /*
         1. 线程对变量进行修改完毕之后，要立刻写回到主存中
         2. 线程对一些变量读取的时候，要从主内存中读取，而不是缓存！
         */
    private volatile static int num = 0;

    //main线程
    public static void main(String[] args) throws InterruptedException {

        //自己的线程！
        new Thread(()->{
            while (num == 0){ //此处不编写代码就是为了，让计算机忙的不可开交！

            }
        }).start();

        Thread.sleep(1000);

        num = 1;
        System.out.println(num);
    }

}
