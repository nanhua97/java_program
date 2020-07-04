package SingletonPattern;

public class SingleTon_5 {
    public static void main(String[] args) {
        SingleTon_E instance1 = SingleTon_E.getInstance();
        SingleTon_E instance2 = SingleTon_E.getInstance();

        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
        System.out.println(instance1==instance2);

    }
}

/**
 * DoubleCheck,双重检查模式,较Synachronized方法效率高
 * 线程安全
 * 推荐使用
 */
class SingleTon_E{
    private static volatile SingleTon_E singleTon_e;
    private SingleTon_E(){}
    public static SingleTon_E getInstance() {
        if (singleTon_e==null){
            synchronized (SingleTon_E.class){
                if (singleTon_e==null){
                    singleTon_e = new SingleTon_E();
                }
            }
        }
        return singleTon_e;
    }
}
