package jvm_test.jvm_exp;

import java.util.ArrayList;

/**
 * 堆内存溢出异常测试
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 */
public class HeapOOM {
    static {}
    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();

        while(true){
            objects.add(new HeapOOM());
        }

    }
}
