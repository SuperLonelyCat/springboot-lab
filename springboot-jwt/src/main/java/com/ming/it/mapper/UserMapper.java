package com.ming.it.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ming.it.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 11119638
 * @date 2020/11/23 19:40
 */
/*
 * BaseMapper<T>直接继承，注入Spring
 * */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
