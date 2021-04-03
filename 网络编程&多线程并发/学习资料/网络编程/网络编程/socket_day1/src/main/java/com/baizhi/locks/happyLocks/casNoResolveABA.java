package com.baizhi.locks.happyLocks;


import java.util.concurrent.atomic.AtomicInteger;

//cas  未解决ABA问题
public class casNoResolveABA {

    //创建初始值为0的原子值   并将其累加至100
    static AtomicInteger ato = new AtomicInteger(0);
    //创建多线程下的并发操作  将  原子值加至100
    public static void addTo100() throws InterruptedException {
        while(ato.get() <100){
            int oldValue = ato.get();
            int newValue = oldValue + 1;
            Thread.sleep(1000);
            if(ato.compareAndSet(oldValue,newValue)){
                System.out.println("======"+Thread.currentThread().getName()+"更新成功！！！"+"值为："+newValue);
            }else{
                System.out.println("******"+Thread.currentThread().getName()+"不能更新！！！");
            }
        }
    }
    public static void main(String[] args) {
        //开10个线程来加至100
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
