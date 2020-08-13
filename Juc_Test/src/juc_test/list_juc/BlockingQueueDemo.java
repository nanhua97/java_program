package juc_test.list_juc;

import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 *      越界Exception    add      remove     element
 *      返回Boolean/null offer    poll       peek
 *      阻塞             put      take
 *      阻塞--超时放弃    offer     poll
 *
 */
public class BlockingQueueDemo {

    private  static ArrayBlockingQueue<Integer> integers = new ArrayBlockingQueue<Integer>(5);

    public static void main(String[] args) {

        //exceptionTest();

        //booleanTest();

        //blockTest();

        overTimeTest();


    }

    private static void overTimeTest() {
        //超时放弃
        for (int i = 0; i < 6; i++) {
            try {
                System.out.println(integers.offer(i, 3L, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //超时放弃
        for (int i = 0; i < 6; i++) {
            try {
                System.out.println(integers.poll(3L, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void blockTest() {
        //队列满时会阻塞
        for (int i = 0; i < 5; i++) {
            try {
                integers.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //队列空时会阻塞
        for (int i = 0; i < 6; i++) {
            try {
                System.out.println(integers.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void booleanTest() {

        //检测队列第一个元素是否为空,为空返回null
        System.out.println(integers.peek());

        //添加元素,添加成功返回true,失败返回false
        for (int i = 0; i < 6; i++) {
            System.out.println(integers.offer(i));
        }

        //添加元素,失败返回null
        for (int i = 0; i < 6; i++) {
            System.out.println(integers.poll());
        }
    }

    /**
     * 队列越界会抛出异常
     */
    private static void exceptionTest() {

        //队列移除元素,越界会异常
        //System.out.println(integers.remove());

        //队列增加元素,越界会异常
        for (int i = 0; i < 5; i++) {
            integers.add(i);
        }

        //检测队列第一个元素是否为空,为空会异常
        System.out.println(integers.element());
    }
}
