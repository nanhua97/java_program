package juc_test.list_juc;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch可以保证某些个线程执行完毕之后再激活某个线程,计数减法
 * 配合方法:
 *      countDownLatch.countDown()
 *      countDownLatch.await()
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        //CountDownLatch countDownLatch = new CountDownLatch(6);
        CountDownLatch countDownLatch = new CountDownLatch(3);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "退出");
                //计数器减一
                countDownLatch.countDown();
            },"Thread-" + String.valueOf(i)).start();
        }
        //阻塞main线程
        countDownLatch.await();
        System.out.println("main线程退出=====");
    }
}
