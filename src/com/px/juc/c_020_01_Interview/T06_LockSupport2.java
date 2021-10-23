package com.px.juc.c_020_01_Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

//使用LockSupport,解决sleep后的问题
public class T06_LockSupport2 {

    // 添加volatile，使t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    static Thread t1 = null,t2 = null;
    public static void main(String[] args) {
        T06_LockSupport2 v = new T06_LockSupport2();



        t1 = new Thread(() -> {
            System.out.println("t1 启动");
            for (int i = 0; i < 10; i++) {
                v.add(new Object());
                System.out.println("add  " + i);
                if (v.size() == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        }, "t1");


        t2 = new Thread(()-> {
            System.out.println("t2启动");
            //if(v.size() != 5) {
                LockSupport.park();

           // }
            System.out.println("t2 结束");
            LockSupport.unpark(t1);

        },"t2");

        t1.start();
        t2.start();
    }
}
