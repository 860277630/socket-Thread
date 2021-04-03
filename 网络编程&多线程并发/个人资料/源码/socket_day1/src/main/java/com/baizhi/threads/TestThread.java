package com.baizhi.threads;

import java.sql.SQLOutput;

public class TestThread {


    public static void main(String[] args) {


        //创建多线程  方式一: 实现runnable   方式二: 继承Thread类    方式三: 线程池


        //方式一:  实现runnable接口   run方法
        /*MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();*/
        /*new Thread(new Runnable() {
            public void run() {
                System.out.println("线程名称:　"+Thread.currentThread().getName());
            }
        }).start();*/

        //方式二:  extends Thread类
        MyThread myThread = new MyThread();
        myThread.run();
        myThread.start();


    }

}
