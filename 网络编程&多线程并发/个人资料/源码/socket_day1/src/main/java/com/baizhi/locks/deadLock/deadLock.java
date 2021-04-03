package com.baizhi.locks.deadLock;

public class deadLock {

    static Object a = new Object();
    static Object b = new Object();
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                synchronized (a){
                    System.out.println(Thread.currentThread().getName()+"i have got a");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"i want to get a");
                    synchronized (b){
                        System.out.println(Thread.currentThread().getName()+"i have got b");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                synchronized (b){
                    System.out.println(Thread.currentThread().getName()+"i have got b");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"i want to get a");
                    synchronized (a){
                        System.out.println(Thread.currentThread().getName()+"i have got a");
                    }
                }
            }
        }).start();


    }
}
