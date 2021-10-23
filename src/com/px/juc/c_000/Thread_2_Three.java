package com.px.juc.c_000;

public class Thread_2_Three {
    public static void main(String[] args) {
       //testSleep();
       //testYield();
        testJoin();
    }

    //使用当前正在执行的线程休眠millis秒,线程处于阻 塞状态
    static void testSleep(){
        new Thread(()->{
            for (int i = 0; i <100 ; i++) {
                System.out.println("A"+i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
/*    当前正在执行的线程暂停一次，允许其他线程执
    行,不阻塞，线程进入就绪状态,如果没有其他等待
    执行的线程，这个时候当前线程就会马上恢复执
    行。通俗的讲让出一下cpu*/
    static void testYield(){
        new Thread(()->{
            for (int i = 0; i <100 ; i++) {
                System.out.println("A"+i);
                if (i%10==0) Thread.yield();
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i <100 ; i++) {
                System.out.println("--------------B"+i);
                if (i%10==0) Thread.yield();
            }
        }).start();
    }

//    调用该方法的线程强制执行，其它线程处于阻塞
//    状态，该线程执行完毕后，其它线程再执行
    static void testJoin(){
       Thread t1 = new Thread(()->{
            for (int i = 0; i <100 ; i++) {
                System.out.println("A"+i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread t2 =new Thread(()->{
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i <100 ; i++) {
                System.out.println("--------------B"+i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        t1.start();
        t2.start();
    }
}
