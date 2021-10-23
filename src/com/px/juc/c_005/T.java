package com.px.juc.c_005;

/**
 * 分析一下这个程序的输出
 * @author mashibing
 */

public class T implements Runnable {
    private /*volatile*/ int count = 100;//跟volatile没有太大关系，这里提一下后面讲

  /* 当不加synchronized时，一个线程运行count-- 假设为99，再输出之前。但是此时又有另外一个进来
     ，运行count--此时就变成98了，此时输出的就是98了，所以要加synchronized
     synchronized保证了。原子性和可见性*/
    @Override
    public /*synchronized*/ void run() {
           count--;
        System.out.println(Thread.currentThread().getName()+" count="+count);
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i <100 ; i++) {
            new Thread(t,"THREAD" + i).start();
        }
    }
}
