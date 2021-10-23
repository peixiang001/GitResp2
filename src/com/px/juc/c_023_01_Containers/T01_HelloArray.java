package com.px.juc.c_023_01_Containers;

import java.util.Arrays;

//list
public class T01_HelloArray {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7,8};

        Arrays.stream(a).map(i -> i + 1).forEach(i -> System.out.println(i + " "));
    }
}
