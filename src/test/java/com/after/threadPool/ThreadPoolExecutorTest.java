package com.after.threadPool;

import com.after00.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
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

}
