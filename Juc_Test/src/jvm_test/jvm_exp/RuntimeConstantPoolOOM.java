package jvm_test.jvm_exp;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * 运行时常量池导致OOM
 * JDK7及之前
 * 限制永久代大小可简介限制常量池容量
 * -XX:PermSize=6M -XX:MaxPermSize=6M
 * JDK8之后将运行时常量池移到了堆
 * -Xms6M -Xmx6M
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        Set<String> stringHashSet = new HashSet<String>();

        short i = 0;

        while (true){
            stringHashSet.add(String.valueOf(i++).intern());
        }

    }
}
