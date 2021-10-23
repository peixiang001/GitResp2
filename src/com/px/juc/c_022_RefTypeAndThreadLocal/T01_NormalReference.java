package com.px.juc.c_022_RefTypeAndThreadLocal;

import java.io.IOException;

//强引用
public class T01_NormalReference {
    public static void main(String[] args) throws IOException {
        M m = new M();//引用m指向一个对象M()，就不会被回收
        //m = null;//引用m设置为空，不在指向对象，就会被回收。
        System.gc();//DisableExplicitGC

        System.in.read();//阻塞线程查看gc回收情况
    }
}
