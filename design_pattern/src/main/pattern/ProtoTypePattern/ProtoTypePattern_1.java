package ProtoTypePattern;

/**
 * 原型模式一,实现Cloneable接口
 * 但是多层嵌套对象克隆会比较复杂
 */
public class ProtoTypePattern_1 {

    public static void main(String[] args) {

        Student student2 = new Student("张三", 30, null);

        Student student = new Student("张三", 20,student2);

        Student student1 = (Student)student.clone();

        System.out.println(student.hashCode());
        System.out.println(student1.hashCode());

        System.out.println(student.getStudent());
        System.out.println(student1.getStudent());

    }

}

class Student implements Cloneable{
    private String name;
    private Integer age;
    private Student student;

    public Student(String name, Integer age,Student student) {
        this.name = name;
        this.age = age;
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    protected Object clone(){

        try {
            Student clone = (Student)super.clone();
            Student clone1 = null;
            if (clone.student!=null){
                clone1 = (Student) clone.student.clone();
            }
            clone.setStudent(clone1);
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}