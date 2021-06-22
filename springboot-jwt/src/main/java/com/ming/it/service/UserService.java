package com.ming.it.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ming.it.constant.SqlConstant;
import com.ming.it.entity.User;
import com.ming.it.mapper.UserMapper;
import com.ming.it.vo.UserVO;
import org.springframework.stereotype.Service;

/**
 * @author 11119638
 * @date 2021/6/22 15:31
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    public User queryUser(UserVO userVO) {
        return getBaseMapper().selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUid, userVO.getUid())
                .eq(User::getPassword, userVO.getPassword())
                .last(SqlConstant.LIMIT_ONE));
    }

    public User queryUserById(String uid) {
        return getBaseMapper().selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUid, uid)
                .last(SqlConstant.LIMIT_ONE));
    }
}
