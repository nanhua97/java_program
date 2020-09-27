package juc_test.list_juc;

import java.util.concurrent.*;

class ThreadCase extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

class ThreadCase2 implements Callable{

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return "ThreadCase2";
    }
}

public class ThreadDemo {

    public static void main(String[] args) throws Exception {
        ThreadCase threadCase = new ThreadCase();
        threadCase.start();

        Integer integer = 10;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println(integer);
            }
        };
        Thread threadCase1 = new Thread(runnable);
        threadCase1.start();

        ThreadCase2 threadCase2 = new ThreadCase2();
        FutureTask futureTask = new FutureTask(threadCase2);
        futureTask.run();
        String s = (String) futureTask.get();
        System.out.println(s);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 3, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        try {
            threadPoolExecutor.execute(()->{
                System.out.println(Thread.currentThread().getName());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            threadPoolExecutor.shutdown();
        }

    }
}
