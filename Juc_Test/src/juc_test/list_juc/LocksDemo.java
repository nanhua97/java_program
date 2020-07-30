package juc_test.list_juc;

import java.util.concurrent.TimeUnit;

class Phone{

    private String phoneOwner;

    public Phone(String phoneOwner) {
        this.phoneOwner = phoneOwner;
    }

    public static synchronized void sendMes(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发送短信");
    }

    public static synchronized void sendMail(){
        System.out.println("发送邮件");
    }

    public void call(){
        System.out.println(phoneOwner + "打电话");
    }

}

/**
 * 使用synchronized修饰的方法,默认锁定的是本对象
 * 使用synchronized修饰的静态方法,锁定的是本类对象
 */
public class LocksDemo {
    public static void main(String[] args) {
        Phone phone1 = new Phone("张三");
        Phone phone2 = new Phone("李四");

        new Thread(()->{
            phone1.sendMes();
        },"phone1").start();

        new Thread(()->{
            phone2.sendMail();
        },"phone2").start();

    }
}
