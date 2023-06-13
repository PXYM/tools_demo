package com.jf.optional;

import com.jf.optional.pojo.Book;
import com.jf.optional.pojo.Student;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @author seinfish
 * @version 1.0
 * @description TODO
 * @date 2023/6/10 21:46
 */
public class OptionalTest {

    public Student getStu(){
        return null;
    }

    @Test
    public void testOfNullable(){
        Student student = new Student();

        Optional<Student> studentOpt = Optional.ofNullable(student);
        studentOpt.ifPresent(stuOpt->{
            System.out.println( "notnull：" + stuOpt);
        });
        studentOpt.map(Student::getBook).ifPresent( bookOpt -> {
            System.out.println("notnull：" + bookOpt);
        });
        String bookId = studentOpt.map(Student::getBook).map(Book::getBookId).orElseGet(() -> "a");
        System.out.println(bookId);

    }
}
