package java_base.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {
    public static void main(String[] args) {
        Class clazz = getStudentClass();
        Student studentObject = getStudentObject(clazz);
        Method studentMethod = getStudentMethods(clazz);
        try {
            studentMethod.invoke(studentObject,10);
            System.out.println(studentObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getStudentField(clazz);
    }

    private static void getStudentField(Class clazz) {
        try {
            //  获取类属性，可获取私有属性
            Field id1 = clazz.getDeclaredField("id");
            Field[] declaredFields = clazz.getDeclaredFields();
            //  获取类属性，不可获取私有属性
            Field id = clazz.getField("id");
            Field[] fields = clazz.getFields();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static Method getStudentMethods(Class clazz) {
        //  获取类的所有方法
        Method[] methods = clazz.getDeclaredMethods();

        try {
            //  根据方法名获取单个方法(可获取私有方法)
            Method getId = clazz.getDeclaredMethod("getId");
            Method setId = clazz.getDeclaredMethod("setId", Integer.class);
            //  不可获取私有方法
            Method getName = clazz.getMethod("getName");
            Method setName = clazz.getMethod("setName", String.class);

            return setId;

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Student getStudentObject(Class clazz) {
        try {
            //  创建对象方式一:根据构造方法创建对象
            //  有参构造
            Constructor constructor = clazz.getConstructor(Integer.class, String.class, int.class);
            Student student = (Student) constructor.newInstance(12, "张三", 12);
            //  无参构造
            Constructor constructor1 = clazz.getConstructor();
            Student student2 = (Student) constructor1.newInstance();

            //  创建对象方式二:根据类对象创建实例对象
            Student student1 = (Student) clazz.newInstance();

            return student1;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Class getStudentClass() {
        //  获取类对象一
        Student student = new Student();
        Class clazz = student.getClass();

        //  获取类对象二
        Class clazz1 = Student.class;

        //  获取类对象三
        try {
            Class clazz2 = Class.forName("java_base.reflect.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return clazz;
    }
}
