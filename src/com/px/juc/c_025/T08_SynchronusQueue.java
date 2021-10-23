package com.px.juc.c_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;


//一个线程给另一个线程下达任务,传递
public class T08_SynchronusQueue { //容量为0
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.put("aaaa");//阻塞等待消费者消费
        //strs.put("bbb");
        //strs.add("aaa");
        //strs.take();
        System.out.println(strs.size());

    }
}
