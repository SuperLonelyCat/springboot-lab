package com.ming.it.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ming.it.mybatis.plus.constant.SqlConstant;
import com.ming.it.mybatis.plus.entity.User;
import com.ming.it.mybatis.plus.mapper.UserMapper;
import com.ming.it.mybatis.plus.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 11119638
 * @date 2021/5/11 15:38
 */
@Service
public class UserServiceByExtensionImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public int insert(User user) {

        user.setCreateAt(new Date());
        user.setModifiedAt(new Date());
        return getBaseMapper().insert(user);
    }

    @Override
    public int delete(String code) {

        return getBaseMapper().delete(Wrappers.<User>lambdaQuery().eq(User::getCode, code).last(SqlConstant.LIMIT_ONE));
    }

    @Override
    public int update(User user) {

        user.setModifiedAt(new Date());
        return getBaseMapper().update(user, Wrappers.<User>lambdaQuery().eq(User::getCode, user.getCode()));
    }

    @Override
    public User getUserByCode(String code) {

        return getBaseMapper().selectOne(Wrappers.<User>lambdaQuery().eq(User::getCode, code).last(SqlConstant.LIMIT_ONE));
    }
}
