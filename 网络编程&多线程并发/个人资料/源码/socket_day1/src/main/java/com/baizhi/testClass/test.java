package com.baizhi.testClass;

import javax.sound.sampled.Line;


//匿名内部类测试
public class test {

    public static void main(String[] args) {
        new aa().info(new IFly() {
            public void fly() {
                System.out.println("实现了匿名内部类！！");
            }
        });
    }
}
