package jvm_test.jvm_exp;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 操作直接内存导致得OOM
 * -Xms20M -Xmx20M -XX:MaxDirectMemorySize=10M
 * Exception in thread "main" java.lang.OutOfMemoryError
 *  at sun.misc.Unsafe.allocateMemory(Native Method)
 *
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024*1024;

    public static void main(String[] args) throws IllegalAccessException {

        Field field = Unsafe.class.getDeclaredFields()[0];

        field.setAccessible(true);

        Unsafe unsafe = (Unsafe)field.get(null);

        while (true){
            unsafe.allocateMemory(_1MB);
        }

    }
}
