package SingletonPattern;

public class SingleTon_6 {
    public static void main(String[] args) {
        SingleTon_F instance1 = SingleTon_F.getInstance();
        SingleTon_F instance2 = SingleTon_F.getInstance();

        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
        System.out.println(instance1==instance2);

    }
}

/**
 * 静态内部类方法,静态内部类在内部类使用到的时候加载
 * 加载的时候创建单例对象
 * 满足了按需加载与线程安全
 * 推荐使用
 */
class SingleTon_F{
    private static SingleTon_F singleTon_f;

    private SingleTon_F(){

    }

    public static SingleTon_F getInstance(){
        return InnerInstance.INSTANCE;
    }

    private static class InnerInstance{
        private static final SingleTon_F INSTANCE = new SingleTon_F();
    }
}
