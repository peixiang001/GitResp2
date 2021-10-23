package com.px.juc.c_028_FalseSharing;


//缓存行不对齐时，线程1和线程2的long类型的x只占8个字节，一共16个字节，
// 一个缓存行64字节，所以两者就会在同一缓存行，此时执行的效率就偏低
public class T01_CacheLinePadding {
    private static class T {
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
