package com.ming.it.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 11119638
 * @date 2021/6/19 14:47
 */
@RestController
@Slf4j
public class UserController {

    @GetMapping("/get")
    public String getName(@RequestParam String name) {
        log.warn("name - {}", name);
        return "name - " + name;
    }
}
