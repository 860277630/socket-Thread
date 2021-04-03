package com.baizhi.threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestThreadPool001 {

    public static void main(String[] args) {

        //无界线程池，可以进行自动线程回收   线程数目没有限制
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        //固定线程池  线程池中线程数固定
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

        //单一线程池  所有任务均在一个线程中执行
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();

        //提交线程任务

        Future<?> submit = cachedThreadPool.submit(new Runnable() {
            public void run() {
                System.out.println("===============");
            }
        });


        System.out.println(submit.isDone());


    }
    
}
