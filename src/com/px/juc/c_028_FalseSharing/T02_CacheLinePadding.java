package com.px.juc.c_028_FalseSharing;

//使用类Padding提前new出七个long类型的对象，用Padding提前占好8*7=56个字节
//此时用T继承Padding，所以T在缓存行中只剩下8个字节，一个long类型的x刚好占满
//这时候就已经对齐了
public class T02_CacheLinePadding {
    private static class Padding {
        public volatile long p1, p2, p3, p4, p5, p6, p7;
    }
    private static class T extends Padding {
        public volatile  long x = 0L;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 1000_0000L; i++) {
                arr[0].x = i;
            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 1000_0000L; i++) {
                arr[1].x = i;
            }
        });

        final  long start = System.nanoTime();

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println((System.nanoTime() - start)/100_0000);
    }
}

