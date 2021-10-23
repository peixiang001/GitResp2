package com.px.juc.c_023_02_FromHashtableToCHM;

import java.util.*;

//提供的线程安全的HashMap类,速递不快
public class T03_TestSynchronizedHashMap {
    static Map<UUID,UUID> m = Collections.synchronizedMap(new HashMap<UUID,UUID>()) ;


    static int count = Constants.COUNT;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static final int THREAD_COUNT = Constants.THREAD_COUNT;

    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread {
        int start;//初始int,从哪里开始
        //1000000个数，一共100个线程。，每个线程负责10000个数
        //记录值存储的数值
        int gap = count/THREAD_COUNT;
        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            //每次存储10000个值
            for (int i = start; i < start+gap; i++) {
                m.put(keys[i],values[i]);
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[THREAD_COUNT];

        //启动100个线程，每次线程的起始位置不一样i=start。
        //每次线程都取10000个数值，put到Hashtable中
        for (int i = 0; i <threads.length ; i++) {
            threads[i] = new MyThread(i * (count/THREAD_COUNT));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);

        System.out.println(m.size());

        //----------------------------------

        start = System.currentTimeMillis();
        //每次读取第十个数
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000000; j++) {
                    m.get(keys[10]);
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
