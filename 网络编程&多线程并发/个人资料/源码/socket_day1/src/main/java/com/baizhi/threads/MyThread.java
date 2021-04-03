package com.baizhi.threads;

public class MyThread extends  Thread {
    @Override
    public void run() {
        System.out.println("线程名称:　"+ Thread.currentThread().getName());
    }
}
