package com.baizhi.locks.happyLocks;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class VersionComparison {
    //实现乐观锁的方法
    //1.比对版本号
    //2.cas   底层也是比对版本号

    //公用数据  模拟数据库数据

    static Map<String, Integer> data1 = new HashMap<String,Integer>();
    static {
        data1.put("value-1", 0);
        data1.put("version-1",0);
    }

    public static void main(String[] args) {
        //开10个线程  然后将值加至100
        for (int i = 0 ;i <10 ;i++){
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
    //做个高并发的循环  来完成多线程加至100的操作
    public static void addTo100() throws InterruptedException {
        while (data1.get("value-1") < 100){
            Integer value = data1.get("value-1");
            Integer version = data1.get("version-1");
            System.out.println(Thread.currentThread().getName()+"查询得到的值为"+value+"查询得到的版本号为"+value);
            //阻塞1秒  模拟处理业务
            Thread.sleep(1000);
            value++;
            //然后去更新
            if(version.equals(data1.get("version-1"))){
                data1.put("value-1",value);
                //版本号+1
                data1.put("version-1",version+1);
                System.out.println("======"+Thread.currentThread().getName()+"更新成功！！！"+"值为："+value);
            }else{
                System.out.println("******"+Thread.currentThread().getName()+"版本"+value+"占用，不能更新！！！");
            }
        }

    }
}
