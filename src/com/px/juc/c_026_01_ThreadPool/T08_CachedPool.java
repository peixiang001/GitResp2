package com.px.juc.c_026_01_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T08_CachedPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println(executorService);


        for (int i = 0; i < 2; i++) {
            executorService.execute(() ->{
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(executorService);

        TimeUnit.SECONDS.sleep(80);
        System.out.println(executorService);
    }
}
