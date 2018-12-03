package demo;

import bean.Student;
import com.google.common.base.Optional;

public class OptionalUseDemo {
    public static void main(String[] args) {
        Optional<Student> possibleNull = Optional.of(null);
        possibleNull.get();
    }
}
