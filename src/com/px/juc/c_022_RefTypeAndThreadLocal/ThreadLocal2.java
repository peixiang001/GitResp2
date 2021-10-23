package com.px.juc.c_022_RefTypeAndThreadLocal;

import java.util.concurrent.TimeUnit;
/**
 * ThreadLocal线程局部变量
 *
 * ThreadLocal是使用空间换时间，synchronized是使用时间换空间
 * 比如在hibernate中session就存在与ThreadLocal中，避免synchronized的使用
 *
 * 运行下面的程序，理解ThreadLocal
 *
 * @author 马士兵
 */
/*使用ThreadLocal，当我们让线程A sleep2秒后get值，让线程B 在sleep1秒后set值.
此时的A是打印不到值的，结果为空
因为ThreadLocal的set是set到当前的线程的map中，所以其他的线程是访问不到的*/
public class ThreadLocal2  {

    //必须加static，否则主程序无法访问
    //volatile static Person p = new Person();

    static ThreadLocal<Persons> t1 = new ThreadLocal<>();

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(t1.get());
        }).start();

        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t1.set(new Persons());
        }).start();
    }

   static class Persons {
        String name = "px";
    }
}


