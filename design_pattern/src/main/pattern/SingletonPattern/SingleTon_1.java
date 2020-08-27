package SingletonPattern;

public class SingleTon_1 {
    public static void main(String[] args) {
        SingleTon_A instance1 = SingleTon_A.getInstance();
        SingleTon_A instance2 = SingleTon_A.getInstance();

        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
        System.out.println(instance1 == instance2);
    }
}
/**
 * 不管后续是否会用到,类加载时就会创建一个单例对象
 * 饿汉式一
 */
class SingleTon_A{
    private static final SingleTon_A singleTon = new SingleTon_A();
    private SingleTon_A(){}
    public static SingleTon_A getInstance(){
        return singleTon;
    }
}



