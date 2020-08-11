package jvm_test.heap_test;

/**
 * java.lang.StackOverflowError
 */
public class StackTest {
    public static void main(String[] args) {
        testFun();
    }

    public static void testFun(){
        testFun();
    }

}
