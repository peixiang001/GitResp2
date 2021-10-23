package com.px.juc.c_025;

import java.util.PriorityQueue;

//最小堆排序
public class T07_01_PriorityQueque {
    public static void main(String[] args) {
        PriorityQueue<String> q = new PriorityQueue<>();

        q.add("c");
        q.add("e");
        q.add("f");
        q.add("g");
        q.add("l");
        q.add("o");
        for (int i = 0; i < 6; i++) {
            System.out.println(q.poll());
        }

    }
}
