package com.ming.it.mybatis.plus.service;

import com.ming.it.mybatis.plus.entity.User;

/**
 * @author 11119638
 * @date 2021/5/11 10:45
 */
public interface UserService {

    /* ******************************* 【新增】 *********************************** */

    int insert(User user);

    /* ******************************* 【删除】 *********************************** */

    int delete(String code);

    /* ******************************* 【更新】 *********************************** */

    int update(User user);

    /* ******************************* 【查询】 *********************************** */

    User getUserByCode(String code);
}
