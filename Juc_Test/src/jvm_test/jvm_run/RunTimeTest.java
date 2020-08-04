package jvm_test.jvm_run;

/**
 * 虚拟机自带的ClassLoader:
 *      BootStrap Classloader 启动类加载器    C/C++写的
 *      Extension Classloader 扩展类加载器    JDK扩展包加载器 JDK/jre/ext下
 *      AppClassLoader  新建类的类加载器
 *      Java.lang.ClassLoader 可自定义类加载器
 * ClassLoader双亲委派机制:
 *      类加载会从顶级类开始往下扫描
 *      高层类会覆盖底层同名类
 */
public class RunTimeTest {
    public static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime();

        System.out.println("可用的进程/CPU数:" + runtime.availableProcessors());
        Object o = new Object();
        //启动类加载器
        System.out.println("第一层ClassLoader:" + o.getClass().getClassLoader());

        System.out.println("======================================================================");

        RunTimeTest runTimeTest = new RunTimeTest();
        //AppClassLoader
        System.out.println("第一层ClassLoader:" + runTimeTest.getClass().getClassLoader());
        //ExtClassLoader
        System.out.println("第二层ClassLoader:" + runTimeTest.getClass().getClassLoader().getParent());
        //BootStrapClassLoader
        System.out.println("第二层ClassLoader:" + runTimeTest.getClass().getClassLoader().getParent().getParent());

        System.out.println("======================================================================");

    }
}
