package com.px.juc.c_000;

import java.util.concurrent.TimeUnit;

//第一个线程
public class Thread_0_One {

    private static class T1 extends Thread{
        //实现run方法
        @Override
        public void run() {
            for (int i = 0; i <10; i++) {
                try {
                    //TimeUnit是java.util.concurrent包下面的一个枚举类，表示给定单元粒度的时间段
                    //TimeUnit.DAYS          //天
                    //TimeUnit.HOURS         //小时
                    //TimeUnit.MINUTES       //分钟
                    //TimeUnit.SECONDS       //秒
                    //TimeUnit.MILLISECONDS  //毫秒
                    //TimeUnit.NANOSECONDS   //毫微秒
                    //TimeUnit.MICROSECONDS  //微秒
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1");
            }
        }
    }
    //通俗的讲，一个程序的不同执行的路径叫做线程
    public static void main(String[] args) {
        //调用线程方式
        //run()直接方法调用
        //先调用run(),执行结束后，调main中的循环，先输出run中，在输出main中,执行路径只有一条
        //new T1().run();


        //调用Thread中的start方法
        new T1().start();//start和main同时运行，交替输出

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
