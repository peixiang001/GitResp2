package com.px.juc.c_020_01_Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

//使用LockSupport
public class T06_LockSupport {

    // 添加volatile，使t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T06_LockSupport v = new T06_LockSupport();

        Thread t2 = new Thread(()-> {
            System.out.println("t2启动");
            if(v.size() != 5) {
                LockSupport.park();

            }
            System.out.println("t2 结束");

        },"t2");

        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1 启动");
            for (int i = 0; i < 10; i++) {
                v.add(new Object());
                System.out.println("add  " + i);
                if (v.size() == 5 ){
                    LockSupport.unpark(t2);
                }
                //同样的问题当去掉sleep时，还是会出错。因为没有等待，线程就会抢占
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

    }
}
