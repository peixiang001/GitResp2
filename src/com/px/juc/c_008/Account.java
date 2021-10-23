package com.px.juc.c_008;

import java.util.concurrent.TimeUnit;
/**
 * 面试题：模拟银行账户
 * 对业务写方法加锁,对业务读方法不加锁
 * 这样行不行？
 * 不行,都需要加锁
 * 容易产生脏读问题（dirtyRead）
 */

public class Account {
    String name;
    double balance;
   public synchronized void set(String name,double balance){
       this.name = name;
       try {
           Thread.sleep(5000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }

       this.balance = balance;
   }

   public /*synchronized*/ double getBalance(String name){
       return this.balance;
   }

    public static void main(String[] args) {

        Account a = new Account();

        new Thread(()->a.set("zs",100.0)).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance("zs"));
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance("zs"));

    }
}
