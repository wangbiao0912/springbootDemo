package com.after.threadPool;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
public class ThreadPoolExecutorTest {
    @Test
    public void test() {
        ExecutorService executors=Executors.newFixedThreadPool(100);
        for (int i=0;i<300;i++){
            int finalI = i;
            executors.execute(()-> {
                log.info("第  {}   个线程，线程名: {}", finalI,Thread.currentThread().getName());
//                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("--------第  {}   个线程，线程名: {}", finalI,Thread.currentThread().getName());

            });
        }

        try {
            log.info("等待其他线程完成。。。。");
            Thread.sleep(20000);
            log.info("线程全部执行完了。。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     *  定时执行
     */
    @SneakyThrows
    @Test
    public void taskThreadTest(){
        ScheduledExecutorService scheduledThreadPool= Executors.newScheduledThreadPool(3);
        Executors.newFixedThreadPool(1).execute(()->    System.out.println("=========="));
        scheduledThreadPool.schedule(()-> {
                System.out.println("延迟三秒");
        }, 3, TimeUnit.SECONDS);

        scheduledThreadPool.scheduleAtFixedRate(()-> {
                System.out.println("延迟 1 秒后每三秒执行一次");
        },1,3,TimeUnit.SECONDS);
        //初始化线程
        Thread thread=new Thread(()->{
            while (true){
                log.info("程序正在执行中");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        Thread.sleep(2000000);

    }
}
