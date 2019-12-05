package com.after00.copy.ForkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

//测试三种结果
/*
    1. 正常     invoke=500000000500000000   time:24061
    2. forkjoin invoke=500000000500000000   time:13936
    3. stream   invoke=500000000500000000   time:504
 */
public class ForkJoinWorkDemo {
    public static void main(String[] args) {
        test();
    }

    public static void test(){
        //统计时间
        long l = System.currentTimeMillis();

        //ForkPool
        ForkJoinPool forkJoinPool = new ForkJoinPool(); //通过ForkJoinPool执行代码！
        ForkJoinWork task = new ForkJoinWork(0L, 1000000000L);//10亿
        Long invoke = forkJoinPool.invoke(task);
        long l2 = System.currentTimeMillis();

        System.out.println("invoke="+invoke + "time:"+(l2-l));
        //invoke=500000000500000000 time:13936
    }

    public static void test2(){
        //统计时间
        long l = System.currentTimeMillis();

        //普通线程实现
        Long x = 0L;
        Long y = 1000000000L;
        for (Long i = 0L; i <= y; i++) {
            x += i;
        }

        long l2 = System.currentTimeMillis();
        System.out.println("invoke="+x + "time:"+(l2-l));
        // invoke=500000000500000000time:24061
    }

    //Stream
    public static void test3(){
        //统计时间  mapreduce : 拆分
        /*
            100信息
                   === 20
                   === 20
                   === 20
                   === 20
                   === 20
         */
        long l = System.currentTimeMillis();

        long reduce = LongStream.rangeClosed(0, 1000000000L).parallel().reduce(0, Long::sum);

        long l2 = System.currentTimeMillis();

        System.out.println("invoke="+ reduce + "time:"+(l2-l));

        // invoke=500000000500000000time:504
    }

    /*
    为什么要去用？
        400    4人    100
        Fork、Join
            甲
                200
                   乙
                    100
                    100
                200
                   丙
                    100
                    100

     */

}
