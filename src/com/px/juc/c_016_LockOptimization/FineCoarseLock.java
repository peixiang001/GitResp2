package com.px.juc.c_016_LockOptimization;

import java.util.concurrent.TimeUnit;
/**
 * synchronized优化
 * 同步代码块中的语句越少越好
 * 比较m1和m2
 * @author mashibing
 */
class FineCoarseLock {
    int count = 0;
    synchronized void m1(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //业务逻辑中只有下面这句需要sync，这时不应该给整个方法上锁
        count++;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

     void m2(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         //只需要在使用的地方上锁
        synchronized (this){
           count++;
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
