package SingletonPattern;

public class SingleTon_4 {
    public static void main(String[] args) {
        SingleTon_D instance1 = SingleTon_D.getInstance();
        SingleTon_D instance2 = SingleTon_D.getInstance();

        System.out.println(instance1.hashCode());
        System.out.println(instance1.hashCode());
        System.out.println(instance1 == instance2);
    }
}

/**
 * 线程安全,但效率较低,Synchronized是重量级锁
 * 懒汉式二
 */
class SingleTon_D{
    private static SingleTon_D singleTon_d;
    private SingleTon_D(){

    }
    public static synchronized SingleTon_D getInstance(){
        if (singleTon_d == null){
            singleTon_d = new SingleTon_D();
        }
        return singleTon_d;
    }
}
