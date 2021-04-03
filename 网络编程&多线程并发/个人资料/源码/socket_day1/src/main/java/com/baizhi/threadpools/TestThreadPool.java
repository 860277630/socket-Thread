package com.baizhi.threadpools;

import java.util.concurrent.*;

public class TestThreadPool {

    public static void main(String[] args) {

        //创建线程池对象
        //参数1: 线程池中核心线程数量
        //参数2: 线程池中最大线程数
        //参数3: 线程池中线程的空闲时间
        //参数4: 决定线程中线程的空闲时间的单位 TimeUnit
        //参数5: 线程等待队列
        //参数6: 创建线程工厂
        //参数7: 用于被拒绝任务的处理程序
        //     策略一: 直接拒绝 new ThreadPoolExecutor.AbortPolicy()     RejectedExecutionHandler  线程拒绝处理器
        //     策略二: 在调用execute方法的程序中执行该任务 new ThreadPoolExecutor.CallerRunsPolicy()  如果程序已经结束则丢弃任务
        //     策略三: new ThreadPoolExecutor.DiscardOldestPolicy()  丢弃等待队列中的请求，然后重试 execute ，除非执行程序被关闭，在这种情况下，任务被丢弃。
        //     策略四: new ThreadPoolExecutor.DiscardPolicy()    丢弃最后一次无法执行的任务  静默丢弃
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(1);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                                        1,  //核心线程池数量           银行窗口   3
                                    2,  //线程池最大线程数量       临时窗口   5-3
                                       10, //空闲10
                                    TimeUnit.SECONDS,   //单位  秒
                                    workQueue,          //线程等待队列            银行中小板凳  2
                                    threadFactory,      //创建新线程工厂
                                    new ThreadPoolExecutor.DiscardPolicy()); //拒绝处理器

        //调用线程池对象中execute方法执行线程任务

        for (int i = 1; i <=8 ; i++) {
            threadPoolExecutor.execute(new MyRunnable());
        }
       threadPoolExecutor.execute(new Runnable() {
           public void run() {
               System.out.println("任务1    "+Thread.currentThread().getName());
           }
       });


        threadPoolExecutor.execute(new Runnable() {
            public void run() {
                System.out.println("任务2    "+Thread.currentThread().getName());
            }
        });



        threadPoolExecutor.execute(new Runnable() {
            public void run() {
                System.out.println("任务3    "+Thread.currentThread().getName());
            }
        });

        threadPoolExecutor.execute(new Runnable() {
            public void run() {
                System.out.println("任务4    "+Thread.currentThread().getName());
            }
        });




    }

}

class MyRunnable implements  Runnable{
    public void run() {
        System.out.println("任务.......线程名称:"+Thread.currentThread().getName());
        /*try {
            //Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
