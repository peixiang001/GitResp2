package com.px.juc.c_020;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class T09_TestPhaser2 {
    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();
    static void milliSleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        phaser.bulkRegister(7);
        for (int i = 0; i < 5; i++) {
            new Thread(new Person("p" + i)).start();
        }
        new Thread(new Person("xl")).start();
        new Thread(new Person("xn")).start();
    }
    //重写Phaser的onAdvance(),满足条件则调用onAdvance方法
    static class MarriagePhaser extends Phaser {
        @Override                   //什么阶段      //有几个
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("所有人到齐了！" + registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人吃完了！" + registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人离开了！" + registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("婚礼结束！新郎新娘抱抱！" + registeredParties);
                    return true;
                default:
                    return true;

            }
        }
    }

    static class Person implements Runnable {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
            milliSleep(r.nextInt(100));
            System.out.printf("%s 到达现场！\n", name);
            //arriveAndAwaitAdvance相当于CyclicBarrier的await()
            phaser.arriveAndAwaitAdvance();
        }

        public void eat() {
            milliSleep(r.nextInt(100));
            System.out.printf("%s 吃完！\n", name);
            phaser.arriveAndAwaitAdvance();
        }

        public void leave() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 离开！\n", name);
            phaser.arriveAndAwaitAdvance();
        }

        private void hug() {
            if (name.equals("xn") || name.equals("xl") ){
                milliSleep(r.nextInt(1000));
                System.out.printf("%s 洞房！\n", name);
                phaser.arriveAndAwaitAdvance();
            } else {
                phaser.arriveAndDeregister();//结束，注销
                //phaser.register()
            }
        }

        @Override
        public void run() {
            arrive();

            eat();

            leave();

            hug();

        }
    }
}
