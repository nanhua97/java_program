package juc_test.list_juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class PrintWord{

    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void printA(){
        lock.lock();
        try {
            while (number != 1){
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "\t1");
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try {
            while (number != 2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "\t2");
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try {
            while (number != 3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "\t3");
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

/**
 * 三个线程A-->B-->C
 * 可为一个Lock配置多个Condition,达到线程按序启动的效果
 */
public class ConditionDemo {
    public static void main(String[] args) {

        PrintWord printWord = new PrintWord();

        new Thread(()->{
            for (int i = 10; i > 0; i--) {
                printWord.printA();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 10; i > 0; i--) {
                printWord.printB();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 10; i > 0; i--) {
                printWord.printC();
            }
        },"C").start();

    }
}
