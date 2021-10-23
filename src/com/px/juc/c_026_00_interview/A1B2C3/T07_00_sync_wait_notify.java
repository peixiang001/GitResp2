package com.px.juc.c_026_00_interview.A1B2C3;

import java.util.concurrent.CountDownLatch;

//保证t2在t1之前打印，也就是说保证首先输出的是A而不是1
public class T07_00_sync_wait_notify {
    private static volatile boolean t2Started = false;

    //private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        final Object o = new Object();

        char[] a1 = "1234567".toCharArray();
        char[] a2 = "abcdefg".toCharArray();

        new Thread(() -> {
            //latch.await();
            synchronized (o) {
                while (!t2Started){
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (char c : a1) {
                    System.out.println(c);
                    //latch.countDown()
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        },"t1").start();

        new Thread(() -> {
            synchronized (o) {
                for (char c : a2) {
                    System.out.println(c);
                    t2Started = true;
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        },"t2").start();

    }
}
