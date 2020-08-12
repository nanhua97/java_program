package juc_test.list_juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 加Lock锁会导致读写操作都被锁
 * ReadWriteLock:写操作加锁
 */

class TestDB{
    private Map map = new HashMap<String,Object>();

    private ReadWriteLock lock = new ReentrantReadWriteLock();

//    private Lock lock = new ReentrantLock();

    public void put(String key,Object val){
        //lock.writeLock().lock();
//        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t开始写入" + key);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,val);
            System.out.println(Thread.currentThread().getName() + "\t写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
//            lock.unlock();
        }
    }

    public void get(String key){
        lock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "\t开始读取");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读取完成" + o);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

}


/**
 * 线程   操作  资源类
 * 读写锁(目的与作用):
 *      读--读不互斥
 *      读--写互斥
 *      写--写互斥
 * 锁升级: 读锁变成写锁(在读锁释放前申请写锁)
 * 锁降级: 写锁变成读锁(在写锁释放前盛情读锁)
 *
 * ReentrantReadWriteLock支持锁降级,不支持锁升级(会造成死锁)
 *
 * 进入读锁条件:
 *      没有其他线程持有写锁,没有写请求(除非写请求为当前线程)
 * 进入写锁条件:
 *      没有其他线程持有读/写锁
 *
 */
public class ReadWriterLockDemo {
    public static void main(String[] args) {
        readWriteLockTest();
    }

    private static void readWriteLockTest() {
        TestDB testDB = new TestDB();

        for (int i = 0; i < 5; i++) {
            final int tmp = i;
            new Thread(()->{
                testDB.put(tmp+"",tmp+"");
            }).start();
        }

        for (int i = 0; i < 5; i++) {
            final int tmp = i;
            new Thread(()->{
                testDB.get(tmp+"");
            }).start();
        }
    }
}
