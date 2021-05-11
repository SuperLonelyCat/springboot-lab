package com.ming.it.mybatis.plus;

import com.ming.it.mybatis.plus.entity.User;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public abstract class SpringbootMybatisPlusApplicationTests {

    protected User initUser() {

        return User.builder()
                .code(UUID.randomUUID().toString())
                .name("SuperLonelyCat")
                .age(25)
                .email("FLMnjupt@163.com")
                .build();
    }
}
