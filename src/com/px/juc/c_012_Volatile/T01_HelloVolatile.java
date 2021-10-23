package com.px.juc.c_012_Volatile;

import java.util.concurrent.TimeUnit;

/**
 * volatile 关键字，使一个变量在多个线程间可见
 * A B线程都用到一个变量，java默认是A线程中保留一份copy，这样如果B线程修改了该变量，则A线程未必知道
 * 使用volatile关键字，会让所有线程都会读到变量的修改值
 *
 * 在下面的代码中，running是存在于堆内存的t对象中
 * 当线程t1开始运行的时候，会把running值从内存中读到t1线程的工作区，在运行过程中直接使用这个copy，并不会每次都去
 * 读取堆内存，这样，当主线程修改running的值之后，t1线程感知不到，所以不会停止运行
 *
 * 使用volatile，将会强制所有线程都去堆内存中读取running的值
 *
 * 可以阅读这篇文章进行更深入的理解
 * http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html
 *
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 * @author mashibing
 *
 *
 * 1,可见性，是指线程之间的可见性，一个线程修改的状态对另一个线程是可见的。
 * 2,有序性：Java 语言提供了 volatile 和 synchronized 两个关键字来保证线程之间操作
 *                                       的有序性，volatile 是因为其本身包含“禁止指令重排序”的语义，为了保证数据的一致性
 *
 *注意volatile不能替代synchronized
 *
 *
 * 什么是禁止指令重排序：

 *
 *假设存在int a，申请内存时默认值是0，改值初始化值a=8，然后赋值a=8
 * 如果出现重排序，改值和赋值的位置改变，那么a就不是8是0了。此时一个线程
 * 访问时就得不到想要的数据了
 * jvm new对象分三步 申请内存->改值->赋值->
 *
 *
 *
 */
public class T01_HelloVolatile {
    //对比一下有无volatile的情况下，整个程序运行结果的区别
    volatile boolean running = true;
    void m(){
        System.out.println("m start");
        while (running){

        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        T01_HelloVolatile t = new T01_HelloVolatile();
        new Thread(t::m,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.running =false; //当running =false停止程序
    }
}
