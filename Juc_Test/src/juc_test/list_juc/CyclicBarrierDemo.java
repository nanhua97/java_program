package juc_test.list_juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier:当线程数达到某个阈值时激活某线程,计数加法
 * 配合方法:
 *      new Cyclicbarrier(1,()->{System.out.printLn("test");})
 *      cyclicbarrier.await()
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        cyclicBarricTest();
    }

    private static void cyclicBarricTest() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,()->{
            System.out.println("CyclicBarrier线程启动");
        });

        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "启动");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"Thread-" + String.valueOf(i)).start();
        }
    }
}
