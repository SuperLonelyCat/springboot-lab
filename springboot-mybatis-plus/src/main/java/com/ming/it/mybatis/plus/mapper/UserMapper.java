package com.ming.it.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ming.it.mybatis.plus.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 11119638
 * @date 2020/11/23 19:40
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
