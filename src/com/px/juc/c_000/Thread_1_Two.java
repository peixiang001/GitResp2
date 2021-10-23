package com.px.juc.c_000;

public class Thread_1_Two {
    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("Thread");
        }
    }

    static class MyRun implements Runnable{

        @Override
        public void run() {
            System.out.println("Runnable");
        }
    }


    public static void main(String[] args) {
        //第一种使用继承Thread方式创建线程
        new MyThread().start();
        //第二种实现Runnable接口方式创建线程,需要new Thread将new MyRun()传进去，才能start
        MyRun myRun = new MyRun();
        Thread thread = new Thread(myRun);
        thread.start();

        new Thread(new MyRun()).start();

        //lambda表达式写法，也是通过Runnable变形
        new Thread(()->{
            System.out.println("Lambda");
        }).start();

    }
}
//请你告诉我启动线程的三种方式 1：Thread 2: Runnable 3:Executors.newCachedThrad
//还有一种带有返回值的实现方式：Callable。实际上一共有四种