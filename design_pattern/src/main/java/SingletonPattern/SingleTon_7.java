package SingletonPattern;

public class SingleTon_7 {
    public static void main(String[] args) {
        SingleTon_G instance1 = SingleTon_G.getInstance();
        SingleTon_G instance2 = SingleTon_G.getInstance();

        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
        System.out.println(instance1==instance2);
    }
}
/**
 * 线程安全,但效率较低,Synchronized是重量级锁
 * 懒汉式三
 */
class SingleTon_G{
    private static SingleTon_G singleTon_g;

    private SingleTon_G(){

    }
    public static SingleTon_G getInstance(){
        synchronized (SingleTon_G.class){
            if (singleTon_g==null){
                singleTon_g = new SingleTon_G();
            }
        }
        return singleTon_g;
    }
}