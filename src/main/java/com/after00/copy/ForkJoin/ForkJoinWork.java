package com.after00.copy.ForkJoin;

//JUC ： 并发编程
import java.util.concurrent.RecursiveTask;

//ForkJoin！  计算====> 定义一些规则
public class ForkJoinWork extends RecursiveTask<Long> {



    private Long start; //开始值  1
    private Long end; //结束值    10000000

    public static final Long critcal = 100000L; //lin界点

    public ForkJoinWork(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    // 计算，一般用于大数据量的计算！Stream！
    @Override
    protected Long compute() {
        // 是否拆分完毕
        long length = end - start;
        if (length<critcal){ //没有到达临界值
            Long sum = 0L;
            for (Long i = start; i <=end; i++) {
                sum += i;
            }
            return sum;
        }else {
            //拆分任务
            Long middle = (end+start)/2;
            ForkJoinWork right = new ForkJoinWork(start, middle); //1. 第一条线
            right.fork(); //分支
            ForkJoinWork left = new ForkJoinWork(middle+1, end); //2. 第二条线
            left.fork(); //分支

            //合并
            return right.join() + left.join();
        }

    }


}
