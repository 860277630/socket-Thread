package com.baizhi.locks.sadLocks;

import java.util.concurrent.locks.ReentrantReadWriteLock;

//读读共享  读写、写写 互斥
public class writeAndReadLocks {
    //读写锁类
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    //共享变量  模拟数据库数据   并加至100
    public static Integer num = 0;

    //创建读锁
    public static void readLock(){
        //开启读锁
        lock.readLock().lock();
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName()+"===读取====数据为："+num);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //解锁
            System.out.println(Thread.currentThread().getName()+"===读取====完毕----是并行的");
            lock.readLock().unlock();
        }
    }
    //创建解锁
    public static void writeLock(){
        //开启写锁
        lock.writeLock().lock();
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName()+"===修改====数据为："+ ++num);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //解锁
            System.out.println(Thread.currentThread().getName()+"===修改====完毕----一个完了另一个才去调取");
            lock.writeLock().unlock();
        }
    }

    //创建多个线程
    public static void main(String[] args) {
        //创建10个线程去读写读
        for(int i = 0; i < 10 ;i++){
            new Thread(()->{
                readLock();
                writeLock();
                readLock();
                },"线程==="+i).start();
        }
        //创建10个线程去写读写
        for(int i = 0; i < 10 ;i++){
            new Thread(()->{
                readLock();
                readLock();
                writeLock();
                },"线程==="+i).start();
        }
    }

}