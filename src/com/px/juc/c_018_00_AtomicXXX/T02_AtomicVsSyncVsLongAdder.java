package com.px.juc.c_018_00_AtomicXXX;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class T02_AtomicVsSyncVsLongAdder {
    static long count2 = 0L;
    static AtomicLong count1 = new AtomicLong(0L);
    static LongAdder count3 = new LongAdder();
    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()-> {
                for (int j = 0; j < 100000; j++) {
                    count1.incrementAndGet();
                }
            });
        }

        long start = System.currentTimeMillis();

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        long end = System.currentTimeMillis();

        System.out.println("Atomic: " + count1.get() + " time " + (end-start));


        //----------------------------------------------------------------
        Object o = new Object();

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()-> {
                for (int j = 0; j < 100000; j++) {
                  synchronized (o) {
                      count2++;
                  }
                }
            });
        }

        start = System.currentTimeMillis();

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        end = System.currentTimeMillis();

        System.out.println("synchronized: " + count1.get() + " time " + (end-start));



        //------------------------------------------------------------------------------

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()-> {
                for (int j = 0; j < 100000; j++) {
                    count3.increment();
                }
            });
        }

        start = System.currentTimeMillis();

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        end = System.currentTimeMillis();

        System.out.println("LongAdder: " + count1.get() + " time " + (end-start));

    }
}
