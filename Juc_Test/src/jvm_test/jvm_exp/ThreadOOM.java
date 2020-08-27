package jvm_test.jvm_exp;

/**
 * -Xms2m -Xmx2m -Xss2m
 * 创建线程导致OOM
 * 此代码会导致系统过载
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 */
public class ThreadOOM {
    private void doNotStop(){
        while (true){

        }
    }

    private void startThread(){
        while (true){
            Thread thread = new Thread(() -> {
                doNotStop();
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        ThreadOOM threadOOM = new ThreadOOM();
        threadOOM.startThread();
    }
}
