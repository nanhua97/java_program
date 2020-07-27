package juc_test.list_juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 故障现象(List,Set,Map)
 *      java.util.ConcurrentModificationException
 * 导致原因
 *      并发修改ArrayList中的元素导致
 * 解决方法
 *      使用Vector,Vector中为大部分操作加了Synchronized
 *      使用工具类Collections的synachronizedList/Set/Map方法,此方法对操作使用了Synchronized
 *      使用CopyOnWriteArrayList/Set/ConcurrentHashMap,其中对操作使用了ReentrantLock对方法加锁
 * 优化建议
 *
 */
public class UnSafeList {
    public static void main(String[] args) {
        //unSafeList();
        //unSafeSet();
        unSafeMap();


    }

    private static void unSafeMap() {
        //Map map = new HashMap();
        //Map map = Collections.synchronizedMap(new HashMap<>());
        Map map = new ConcurrentHashMap();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map.toString());
            },"thread"+String.valueOf(i)).start();
        }
    }

    private static void unSafeSet() {
        //Set set = new HashSet<>();
        //Collections.synchronizedSet(new HashSet<>());
        Set set = new CopyOnWriteArraySet();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(Thread.currentThread().getName());
                System.out.println(set.toString());
            },"thread"+String.valueOf(i)).start();
        }
    }

    private static void unSafeList() {
        //List strings = new ArrayList<String>();
        //List strings = new Vector<>();
        //List strings = Collections.synchronizedList(new ArrayList<>());
        List strings = new CopyOnWriteArrayList();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                strings.add("a");
                System.out.println(strings.toString());
            },"thread"+String.valueOf(i)).start();
        }
    }
}
