package com.baizhi.threads;

public class MyRunnable implements Runnable {
    public void run() {

        System.out.println("线程名称: "+Thread.currentThread().getName());
    }
}
