package SingletonPattern;

public class SingleTon_3 {
    public static void main(String[] args) {
        SingleTon_C instance1 = SingleTon_C.getInstance();
        SingleTon_C instance2 = SingleTon_C.getInstance();

        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
        System.out.println(instance1 == instance2);

    }
}

/**
 * 线程不安全的单例模式
 * 懒汉式一
 */
class SingleTon_C {
    private static SingleTon_C singleTon_c;

    private SingleTon_C() {
    }

    public static SingleTon_C getInstance() {
        if (singleTon_c == null) {
            singleTon_c = new SingleTon_C();
        }
        return singleTon_c;
    }


}
