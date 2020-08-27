package SingletonPattern;

public class SingleTon_2 {
    public static void main(String[] args) {
        SingleTon_B singleTon_b1 = SingleTon_B.getInstance();
        SingleTon_B singleTon_b2 = SingleTon_B.getInstance();

        System.out.println(singleTon_b1.hashCode());
        System.out.println(singleTon_b2.hashCode());
        System.out.println(singleTon_b1 == singleTon_b2);
    }
}

/**
 * 不管后续是否会用到,类加载时就会创建一个单例对象
 * 饿汉式二
 */
class SingleTon_B{
    private static SingleTon_B singleTon_b;
    static {
        singleTon_b = new SingleTon_B();
    }
    private SingleTon_B(){}

    public static SingleTon_B getInstance(){
        return singleTon_b;
    }

}
