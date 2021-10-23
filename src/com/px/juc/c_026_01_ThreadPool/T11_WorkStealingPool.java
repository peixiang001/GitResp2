package com.px.juc.c_026_01_ThreadPool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T11_WorkStealingPool {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newWorkStealingPool();

        System.out.println(Runtime.getRuntime().availableProcessors());

        executorService.execute(new R(1000));
        executorService.execute(new R(2000));
        executorService.execute(new R(3000));
        executorService.execute(new R(4000));//daemon
        executorService.execute(new R(5000));
        //由于产生的是精灵线程（守护线程、后台线程），主线程不阻塞的话，看不到输出
        System.in.read();
    }

    static class R implements Runnable {

        int time;

        R(int t) {
            this.time = t;
        }

        @Override
        public void run() {

            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(time  + " " + Thread.currentThread().getName());

        }

    }
}
