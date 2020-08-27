package SingletonPattern;

public class SingleTon_8 {
    public static void main(String[] args) {
        SingleTon_H instance1 = SingleTon_H.INSTANCE;
        SingleTon_H instance2 = SingleTon_H.INSTANCE;

        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
        System.out.println(instance1==instance2);
    }
}

/**
 * 借助JDK1.5之后的枚举实现单例模式,
 * 不但可以满足线程安全,还可以防止反序列化创建新的对象
 * 推荐使用
 */
enum SingleTon_H{
    INSTANCE;
}