package com.ming.it.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ming.it.mybatis.plus.constant.SqlConstant;
import com.ming.it.mybatis.plus.entity.User;
import com.ming.it.mybatis.plus.mapper.UserMapper;
import com.ming.it.mybatis.plus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 11119638
 * @date 2021/5/11 10:45
 */
@Service
public class UserServiceByInjectionImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User user) {

        user.setCreateAt(new Date());
        user.setModifiedAt(new Date());
        return userMapper.insert(user);
    }

    @Override
    public int delete(String code) {

        return userMapper.delete(Wrappers.<User>lambdaQuery().eq(User::getCode, code).last(SqlConstant.LIMIT_ONE));
    }

    @Override
    public int update(User user) {

        user.setModifiedAt(new Date());
        return userMapper.update(user, Wrappers.<User>lambdaQuery().eq(User::getCode, user.getCode()));
    }

    @Override
    public User getUserByCode(String code) {

        final LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getCode, code);
        lambdaQueryWrapper.last(SqlConstant.LIMIT_ONE);
        return userMapper.selectOne(lambdaQueryWrapper);
    }
}
