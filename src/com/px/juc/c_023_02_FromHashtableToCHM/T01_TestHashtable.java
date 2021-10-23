package com.px.juc.c_023_02_FromHashtableToCHM;

import java.util.Hashtable;
import java.util.UUID;
import java.util.concurrent.locks.Condition;


//Hashtable多线程插入数据全面，但是速度较慢，因为有锁，所以读取数据速度时更慢
public class T01_TestHashtable {
    static Hashtable<UUID,UUID> m = new Hashtable<>();


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
