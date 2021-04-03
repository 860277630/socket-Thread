package com.baizhi.locks.sadLocks;

public class synchronizedLocks {

    //共享变量
    static Integer num = 0;


    //① 非静态方法上的 synchronized相当于  类中的synchronized（this）
    //② 静态方法上的 synchronized相当于  类中的synchronized（synchronizedLocks。class）
    //③ synchronized(a)相当于动态的对a方法或a对象加同步锁


    //①
    public synchronized  void a1(){
        System.out.println("进入同步方法a1!!!");
    }
    public void a2(){
        synchronized (this){
            System.out.println("进入同步方法a2!!!");
        }
    }

    //②
    public static synchronized  void b1(){
        System.out.println("进入同步方法b1!!!");
    }
    public static void b2(){
        synchronized (synchronizedLocks.class){
            System.out.println("进入同步方法b2!!!");
        }
    }


    //③
    public static Object c(){
        System.out.println("进入方法c！！！");
        return null;

    }
    public static void main(String[] args) {
        synchronized (num){
            System.out.println("对num变量加了同步锁！！！");
        }
        synchronized (c()){
            System.out.println("对C方法加了同步锁！！！");
        }
    }
}
