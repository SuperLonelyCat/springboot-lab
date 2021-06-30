package com.ming.it.service;

import com.ming.it.entity.User;

import java.util.List;

/**
 * @author 11119638
 * @date 2021/6/30 16:07
 */
public interface UserService {

    User getById(String uid);

    List<User> getByIds(List<String> uids);

    List<User> getAll();
}
