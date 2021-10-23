package com.px.juc.c_026_01_ThreadPool;

import java.util.concurrent.*;

public class T03_Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return "Hello Callable";
            }
        };

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> submit = executorService.submit(callable);

        System.out.println(submit.get());//阻塞，等待结果返回

        executorService.shutdown();
    }
}
