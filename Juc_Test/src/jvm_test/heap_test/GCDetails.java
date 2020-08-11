package jvm_test.heap_test;

import java.util.Random;

/**
 * OOM:OutOfMemory 堆内存溢出
 */
public class GCDetails {
    public static void main(String[] args) {
        String str = "test";
        while(true){
            str += str + new Random().nextInt(88888888) + new Random(99999999);
        }
    }
}
