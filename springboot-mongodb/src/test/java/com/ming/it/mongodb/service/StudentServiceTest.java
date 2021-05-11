package com.ming.it.mongodb.service;

import com.ming.it.mongodb.SpringbootMongodbApplicationTests;
import com.ming.it.mongodb.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 11119638
 * @date 2021/5/10 19:42
 */
@Slf4j
public class StudentServiceTest extends SpringbootMongodbApplicationTests {

    @Autowired
    private StudentService studentService;

    @Test
    public void testInsert() {

        final Student student = initStudent();
        log.info("data - {}", studentService.insert(student));
        Assertions.assertEquals(student, studentService.findById(student.getId()));
    }
}
