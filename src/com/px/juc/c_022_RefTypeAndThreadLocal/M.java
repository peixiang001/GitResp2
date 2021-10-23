package com.px.juc.c_022_RefTypeAndThreadLocal;

//重写finalize,观察垃圾回收时的状态
public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
