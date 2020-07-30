package juc_test.list_juc;

class Num{

    private Integer number = 0;

    public synchronized void increment() throws InterruptedException {

        // 判断
        if (number != 0 ){
            this.wait();
        }

        // 干活
        number += 1;
        System.out.println("线程" + Thread.currentThread().getName() + "\t" + number);

        //通知
        this.notifyAll();
    }

    public void decrement() throws InterruptedException {

        // 判断
        if (number == 0 ){
            this.wait();
        }

        // 干活
        number -= 1;
        System.out.println("线程" + Thread.currentThread().getName() + "\t" + number);

        //通知
        this.notifyAll();
    }

}

public class Consumer {
    public static void main(String[] args) {
        Num num = new Num();

        new Thread(()->{
            try {
                num.increment();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                num.decrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();


    }
}
