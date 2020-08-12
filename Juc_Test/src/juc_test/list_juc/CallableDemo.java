package juc_test.list_juc;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class CallThread implements Callable{

    @Override
    public Object call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName()+"\tCallable实现的线程");
        return 1;
    }
}

/**
 * 实现Callable接口的类可以用于创建有返回值的线程
 * 可以调用FutureTask的get方法获取到返回值
 * 调用get放法时会阻塞main线程
 */
public class CallableDemo {
    public static void main(String[] args) {
        CallThread callThread = new CallThread();
        FutureTask futureTask = new FutureTask(callThread);

        new Thread(futureTask,"A").start();

        System.out.println("Main Before Call======");

        try {
            System.out.println((Integer) futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Main After Call======");
    }
}
