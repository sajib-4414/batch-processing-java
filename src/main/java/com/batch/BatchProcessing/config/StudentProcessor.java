package com.batch.BatchProcessing.config;

import com.batch.BatchProcessing.student.Student;
import org.springframework.batch.item.ItemProcessor;

//in practice, the item processor will take input class, and output class, Not the same
public class StudentProcessor implements ItemProcessor<Student,Student> {


    @Override
    public Student process(Student student) throws Exception {
        //all the business logic goes here
        student.setId(null);
        return student;
    }
}
