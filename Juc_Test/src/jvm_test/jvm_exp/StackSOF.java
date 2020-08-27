package jvm_test.jvm_exp;

/**
 * -Xss128k
 * 栈溢出
 */
public class StackSOF {
    private int num = 1;
    public void inner(){
        num += 1;
        inner();
    }
    public static void main(String[] args) {
        StackSOF stackSOF = new StackSOF();

        try {
            stackSOF.inner();
        } catch (Exception e) {
            System.out.println("栈深度：" + stackSOF.num);
            e.printStackTrace();
        }
    }
}
