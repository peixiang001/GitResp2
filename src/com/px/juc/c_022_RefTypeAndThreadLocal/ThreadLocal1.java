package com.px.juc.c_022_RefTypeAndThreadLocal;

import java.util.concurrent.TimeUnit;
/**
 * ThreadLocal线程局部变量
 *
 * @author 马士兵
 */

/*当我们让线程A sleep2秒后打印值，让线程B 在sleep1秒后修改值.
A打印的结果就是B修改后的值*/
public class ThreadLocal1 {
    //必须加static，否则主程序无法访问
    volatile static Person p = new Person();

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(p.name);
        }).start();

        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "wj";
        }).start();
    }

}


class Person {

    String name = "px";
}