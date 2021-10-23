package com.px.juc.c_026_00_interview.A1B2C3;

public class T06_00_sync_wait_notify {
    public static void main(String[] args) {
        final Object o = new Object();
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "abcdefg".toCharArray();

        new Thread(() -> {
            synchronized (o) {
                for (char c : a1) {
                    System.out.println(c);
                    try {
                        o.notify();
                        o.wait();//让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //o.notifyAll(); //必须，否则无法停止程序
            }
        }).start();

        new Thread(() -> {
            synchronized (o) {
                for (char c : a2) {
                    System.out.println(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notifyAll();
            }
        }).start();
    }
}
//如果我想保证t2在t1之前打印，也就是说保证首先输出的是A而不是1，这个时候该如何做？