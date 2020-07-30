package juc_test.fun_int;

/**
 * 函数式接口注解声明,FunctionInterface
 */
@FunctionalInterface
interface Fun{
    static void method_1(){
        System.out.println("method_1");
    }
    default void method0(){
        System.out.println("method0");
    }
    void method1();
}

public class MyFunction {
    public static void main(String[] args) {
        funTest();
    }

    private static void funTest() {
        useFun(new Fun() {
            @Override
            public void method1() {
                System.out.println("函数式接口");
            }
        });
        useFun(()->{
                System.out.println("函数式接口");
        });
    }

    public static void useFun(Fun fun){
        fun.method1();
        fun.method0();
        Fun.method_1();
    }
}
