package juc_test.list_juc;

import java.util.concurrent.*;

public class ThreadPoolDemo {

    private static ExecutorService threadPool;

    public static void main(String[] args) {

        //fixThreadPollTest();

        //singleThreadPoolTest();

        cachedThreadPoolTest();
    }

    private static void cachedThreadPoolTest() {
        try {
            //可自动扩容的线程池
            threadPool = Executors.newCachedThreadPool();
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"执行任务");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void singleThreadPoolTest() {
        //只有一个线程的线程池
        threadPool = Executors.newSingleThreadExecutor();

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "执行任务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void fixThreadPollTest() {
        //固定大小的线程池
        threadPool = Executors.newFixedThreadPool(5);

        try {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "执行任务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
