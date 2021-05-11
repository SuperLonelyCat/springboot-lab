package com.ming.it.mongodb;

import com.ming.it.mongodb.entity.Student;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public abstract class SpringbootMongodbApplicationTests {

    protected Student initStudent() {

        return Student.builder()
                .id(UUID.randomUUID().toString())
                .studentCode("123456789")
                .studentName("SuperLonelyCat")
                .courseCode("chinese")
                .disabled(Boolean.FALSE)
                .build();
    }
}
