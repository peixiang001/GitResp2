package com.px.juc.c_024_FromVectorToQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 *
 * 分析下面的程序可能会产生哪些问题？
 *只对方法size(),remove()加锁。但是中间的sleep并没有加锁，还是会出错
 * 当只有一张tickets，但是多线程访问时，还是会都判断tickets > 0
 *
 * 使用Vector或者Collections.synchronizedXXX
 * 分析一下，这样能解决问题吗？
 *
 * @author 马士兵
 */
public class TicketSeller2 {
    static Vector<String> tickets = new Vector<>();

    static {
        for(int i=0; i<1000; i++) tickets.add("票编号：" + i);
    }



    public static void main(String[] args) {
        for(int i=0; i<10; i++) {
            new Thread(()->{
                while(tickets.size() > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("销售了--" + tickets.remove(0));
                }
            }).start();
        }
    }
}
