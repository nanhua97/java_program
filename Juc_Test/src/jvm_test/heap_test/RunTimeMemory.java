package jvm_test.heap_test;

/**
 * Eden:from:to 默认为8:1:1
 * Young:Old 默认为1:2
 *  一半设置总堆内存与最大堆内存相等,
 * -Xms20M -Xmx10M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * -Xms10M -Xmx10M -XX:+PrintGCDetails
 * -Xms 设置总堆内存,默认为物理内存的1/64
 * -Xmx 设置最大堆内存,默认为物理内存的1/4
 * -Xmn 设置新生代所占内存
 * -XX:SurvivorRatio 设置Eden与form/to区比例
 * -XX:+PrintGCDetails  输出GC日志
 */
public class RunTimeMemory {

    private static final int _MB = 1024*1000;

    public static void main(String[] args) {
        long totalMemory = Runtime.getRuntime().totalMemory();

        System.out.println("java总堆内存(默认物理内存的1/64)（-Xms）:" + totalMemory/1024/1024 + "MB");

        long maxMemory = Runtime.getRuntime().maxMemory();

        System.out.println("java最大堆内存(默认物理内存的1/4)（-Xmx）:" + maxMemory/1024/1024 + "MB");

        System.out.println("输出GC日志 -XX:+PrintGCDetails");

        Byte[] bytes1,bytes2,bytes3,bytes4;

        bytes1 = new Byte[3*_MB];

        bytes2 = new Byte[3*_MB];

        //bytes3 = new Byte[4*_MB];

        //bytes4 = new Byte[4*_MB]; //触发一次MinorGC

    }
}
