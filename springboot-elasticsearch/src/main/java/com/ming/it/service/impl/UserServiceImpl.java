package com.ming.it.service.impl;

import com.google.common.collect.Lists;
import com.ming.it.entity.User;
import com.ming.it.repository.UserRepository;
import com.ming.it.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author 11119638
 * @date 2021/6/30 16:08
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getById(String uid) {
        return StringUtils.isBlank(uid) ? new User() : userRepository.findById(uid).orElse(new User());
    }

    @Override
    public List<User> getByIds(List<String> uids) {
        return CollectionUtils.isEmpty(uids)? Collections.emptyList() : Lists.newArrayList(userRepository.findAllById(uids));
    }

    @Override
    public List<User> getAll() {
        return Lists.newArrayList(userRepository.findAll());
    }
}
