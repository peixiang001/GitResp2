package com.px.juc.c_000;


//测试线程状态
public class Thread_4_four {

    static class MyThread extends Thread{
        @Override
        public void run() {
            //获取当前状态getState()
            System.out.println("-----"+this.getState());//RUNNABLE
            for(int i=0; i<10; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(i);
            }
        }
    }
    public static void main(String[] args) {
        Thread t = new MyThread();
        //获取当前线程状态getState()
        System.out.println("===="+t.getState()); //NEW
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(t.getState());//TERMINATED
    }
}
