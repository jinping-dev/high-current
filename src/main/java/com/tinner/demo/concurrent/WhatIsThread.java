package com.tinner.demo.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 什么是线程
 */
public class WhatIsThread {
    private static class T1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1");
            }
        }
    }
    /**
     观察上面程序的数据结果，你会看到字符串“T1”和“Main”的交替输出，这就是程序中有两条不同的执行
     路径在交叉执行，这就是直观概念上的线程，概念性的东西，理解就好，没有必要咬文嚼字的去背文字
     的定义。
     */
    public static void main(String[] args) {
        //new T1().run();
        new T1().start();
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }
    }

}
