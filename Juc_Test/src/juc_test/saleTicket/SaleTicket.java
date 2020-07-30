package juc_test.saleTicket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程       操作      资源类
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                ticket.saleTicket();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                ticket.saleTicket();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                ticket.saleTicket();
            }
        }).start();
    }
}

/**
 * 资源类 = 实例变量 + 实例方法
 */
class Ticket{
    private Integer tickets = 30;
    Lock lock = new ReentrantLock();

    public void saleTicket(){
        lock.lock();
        try {
            if (tickets>0){
                System.out.println(Thread.currentThread().getName()+"卖出第"+tickets--+"张票");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }
}
