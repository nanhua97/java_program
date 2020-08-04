package juc_test.list_juc;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class CallThread implements Callable{

    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"\tCallable实现的线程");
        return 1;
    }
}

/**
 * 实现Callable接口的类可以用于创建有返回值的线程
 */
public class CallableDemo {
    public static void main(String[] args) {
        CallThread callThread = new CallThread();
        FutureTask futureTask = new FutureTask(callThread);

        new Thread(futureTask,"A").start();

        try {
            System.out.println((Integer) futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
