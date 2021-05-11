package com.ming.it.mybatis.plus.service;

import com.ming.it.mybatis.plus.SpringbootMybatisPlusApplicationTests;
import com.ming.it.mybatis.plus.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 11119638
 * @date 2021/5/11 16:00
 */
@Slf4j
public class UserServiceByInjectionImplTest extends SpringbootMybatisPlusApplicationTests {

    @Autowired
    private UserService userServiceByInjectionImpl;

    @Test
    public void testInsert() {
        final User user = initUser();
        log.warn("result - {}", userServiceByInjectionImpl.insert(user));
        final User userByCode = userServiceByInjectionImpl.getUserByCode(user.getCode());
        Assertions.assertEquals(user, userByCode);
    }
}
