package juc_test.list_juc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.*;

public class ThreadDemo1 {
    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>(5),new ThreadPoolExecutor.DiscardPolicy());

        try {
            for (int i = 0; i < 19; i++) {
                int finalI = i;
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("第"+ finalI +"个任务在"+Thread.currentThread().getName()+"中执行");
                            TimeUnit.SECONDS.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        threadPoolExecutor.shutdown();


        /*Class<Student> studentClass = Student.class;
        Field[] declaredFields = studentClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }
        Method[] declaredMethods = studentClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }

        Method setId = studentClass.getDeclaredMethod("setId", int.class);
        Student student = studentClass.newInstance();
        setId.invoke(student,10);*/
    }
}
