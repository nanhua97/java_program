package juc_test.list_juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Num{

    private Integer number = 0;

    //可重入递归的非公平锁
    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void increment(){
        lock.lock();
        try {
            while (number != 0){
                condition.await();
            }
            number += 1;
            System.out.println("线程" + Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrement(){
        lock.lock();
        try {
            while (number == 0){
                condition.await();
            }
            number -= 1;
            System.out.println("线程" + Thread.currentThread().getName() + "\t" + number);
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /*public synchronized void increment() throws Exception {

        // 判断
        *//*if (number != 0 ){
            this.wait();
        }*//*

        while (number != 0 ){
            this.wait();
        }

        // 干活
        number += 1;
        System.out.println("线程" + Thread.currentThread().getName() + "\t" + number);

        //通知
        this.notifyAll();
    }

    public synchronized void decrement() throws Exception {

        // 判断
        *//*if (number == 0 ){
            this.wait();
        }*//*

        while (number == 0 ){
            this.wait();
        }

        // 干活
        number -= 1;
        System.out.println("线程" + Thread.currentThread().getName() + "\t" + number);

        //通知
        this.notifyAll();
    }*/

}

/**
 * synachronized --->  lock
 * wait ---> await
 * notify ---> singal
 *
 * 线程操作资源类
 * 判断/干活/通知
 * 防止虚假唤醒
 */
public class Consumer {
    public static void main(String[] args) {
        Num num = new Num();

        new Thread(()->{
            for (int i = 10; i > 0; i--) {
                try {
                    num.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 10; i > 0; i--) {
                try {
                    num.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 10; i > 0; i--) {
                try {
                    num.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i = 10; i > 0; i--) {
                try {
                    num.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"D").start();

    }
}
