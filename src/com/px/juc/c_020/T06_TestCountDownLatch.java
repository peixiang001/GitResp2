package com.px.juc.c_020;

import java.util.concurrent.CountDownLatch;

public class T06_TestCountDownLatch {
    public static void main(String[] args) {
        usingJoin();
        usingCountDownLatch();
    }

    private static void usingCountDownLatch() {
        Thread[] threads = new Thread[100];
        //记录了threads.length长度的数，倒数计数(门栓概念)
        CountDownLatch latch = new CountDownLatch(threads.length);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()-> {
                int result = 0;
                for (int j = 0; j < 10000; j++) {
                    result += j;
                }
                latch.countDown();//-1
            });
        }

        for (int i = 0; i < threads.length ; i++) {
            threads[i].start();
        }

        try {
            latch.await();//记录threads.length还剩多少
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end latch");
    }

    private static void usingJoin() {
        Thread[] threads = new Thread[100];

        for(int i=0; i<threads.length; i++) {
            threads[i] = new Thread(()->{
                int result = 0;
                for(int j=0; j<10000; j++) result += j;
            });
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("end join");
    }

}
