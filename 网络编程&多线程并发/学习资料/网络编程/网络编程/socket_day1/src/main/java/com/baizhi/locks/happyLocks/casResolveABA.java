package com.baizhi.locks.happyLocks;

import java.util.concurrent.atomic.AtomicStampedReference;

//cas解决ABA问题
public class casResolveABA {
    //带版本号的原子类
    //创建版本号为1 的原子值  0    然后加至100
    static AtomicStampedReference<Integer> reference = new AtomicStampedReference<Integer>(0,1);
    //创建多线程下的并发操作  将  原子值加至100
    public static void addTo100() throws InterruptedException {
        while(reference.getReference() < 100){
            Thread.sleep(500);

            if(reference.compareAndSet(reference.getReference(),reference.getReference()+1,
                    reference.getStamp(),reference.getStamp()+1)){

                System.out.println("======"+Thread.currentThread().getName()+"更新成功！！！"+"值为："+reference.getReference());
            }else{
                System.out.println("******"+Thread.currentThread().getName()+"版本号为"+reference.getStamp()+"不能更新！！！");
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("初始值为："+reference.getReference()+"版本号为："+reference.getStamp());
        //开启10个线程来
        for(int i=0; i<10; i++){
            new Thread(new Runnable() {
                public void run() {
                    System.out.println("线程"+Thread.currentThread().getName()+"启动！！！");
                    try {
                        addTo100();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
