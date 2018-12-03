package demo;

import bean.Student;
import com.google.common.base.Objects;

public class ObjectUseDemo {
    public static void main(String[] args) {
        objEqual();
        System.out.println("-----------------");
        objToString();

    }

    private static void objToString() {
        Student jim = new Student();
        jim.setId(1);
        jim.setName("Jim");
        jim.setAge(13);
        System.out.println(jim.toString());
    }

    private static void objEqual() {
        Object a = null;
        Object b = new Object();
        boolean aEqualsB = Objects.equal(a, b);
        System.out.println(aEqualsB);
    }
}
