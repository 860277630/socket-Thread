package com.baizhi.locks.sadLocks;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import javax.lang.model.element.VariableElement;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockClass {
    //创建共享变量
    static int num = 0;

    //创建锁🔒
    static Lock lock = new ReentrantLock();

    //创建锁
    public static void letLock() {

        System.out.println(Thread.currentThread().getName() + "已准备进入锁！！");

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "已进入锁，并将数据操作为" + ++num);
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放锁！！");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        for(int i = 0 ; i< 5 ;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    letLock();
                }
            },"线程=="+i).start();
        }
    }
}
