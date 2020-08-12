package juc_test.list_juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * semaphore:控制线程数量
 * 配合方法:
 *      semaphore.acquire()
 *      semaphore.release()
 */
public class SemaphoreDemo {
    public static void main(String[] args) {

        semaphoreTest();

    }

    private static void semaphoreTest() {
        Semaphore semaphore = new Semaphore(3);

        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "开始执行...");
                    TimeUnit.SECONDS.sleep(random.nextInt(10));
                    System.out.println(Thread.currentThread().getName() + "执行完毕...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            },"Thread-" + String.valueOf(i)).start();
        }
    }
}
