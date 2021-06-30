package com.ming.it.controller;

import com.ming.it.entity.User;
import com.ming.it.repository.UserRepository;
import com.ming.it.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 11119638
 * @date 2021/6/30 16:57
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/get/byId/{uid}")
    public User getById(@PathVariable String uid) {
        return userService.getById(uid);
    }

    @PostMapping("/get/byIds")
    public List<User> getByIds(@RequestBody List<String> uids) {
        return userService.getByIds(uids);
    }

    @PostMapping("/get/all")
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("/get/byAge/{age}")
    public List<User> getByAge(@PathVariable Integer age) {
        return userRepository.findByAge(age);
    }


    @PostMapping("/get/byName/{name}")
    public List<User> getByName(@PathVariable String name) {
        return userRepository.findByNameEquals(name);
    }

    @PostMapping("/get/byAddress/{address}")
    public List<User> getByAddress(@PathVariable String address) {
        return userRepository.findByAddressLike(address);
    }
}
