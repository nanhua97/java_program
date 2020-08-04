package jvm_test.jvm_run;

/**
 * ClassLoader双亲委派机制:
 *      类加载会从顶级类开始往下扫描
 *      高层类会覆盖底层同名类
 */
public class String {
    public static void main(String[] args) {
        System.out.println("=====================");
    }
}
