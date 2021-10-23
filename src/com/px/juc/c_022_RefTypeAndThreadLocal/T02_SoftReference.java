package com.px.juc.c_022_RefTypeAndThreadLocal;

import java.lang.ref.SoftReference;

/**
 * 软引用
 * 软引用是用来描述一些还有用但并非必须的对象。
 * 对于软引用关联着的对象，在系统将要发生内存溢出异常之前，将会把这些对象列进回收范围进行第二次回收。
 * 如果这次回收还没有足够的内存，才会抛出内存溢出异常。
 * -Xmx20M
 */

/*如果一个对象只具有软引用，则内存空间足够，垃圾回收器就不会回收它；
如果内存空间不足了，就会回收这些对象的内存。只要垃圾回收器没有回收它
，该对象就可以被程序使用。*/
public class T02_SoftReference {
    public static void main(String[] args) {
        //m指向软引用对象SoftReference，SoftReference指向10M的字节数组
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]);
        //m = null;
        System.out.println(m.get());
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());

        //再分配一个数组，heap将装不下，这时候系统会垃圾回收，先回收一次，如果不够，会把软引用干掉
        byte[] b = new byte[1024*1024*15];
        System.out.println(m.get());

    }
}

//软引用非常适合缓存使用
