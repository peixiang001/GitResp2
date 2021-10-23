package com.px.juc.c_020;

import java.util.concurrent.Semaphore;

public class T11_TestSemaphore {
    public static void main(String[] args) {
        //2表示能够同时运行的线程的个数,true表示公平，默认是非公平
        Semaphore sp = new Semaphore(2,true);
        //允许一个线程同时执行
        //Semaphore s = new Semaphore(1);

        new Thread(()->{
            try {
                sp.acquire();//尝试获得到锁，获得到继续执行线程。反之等待。没得到的无法执行
                System.out.println("T1 running...");
                Thread.sleep(200);
                System.out.println("T1 running...");
            }catch (Exception e){
                e.printStackTrace();

            }   finally {
                sp.release();//结束，释放
            }
        }).start();

        new Thread(()->{
            try {
                sp.acquire();

                System.out.println("T2 running...");
                Thread.sleep(200);
                System.out.println("T2 running...");
            }catch (Exception e){
                e.printStackTrace();

            }   finally {
                sp.release();
            }
        }).start();
    }
}
