package com.ming.it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 使@WebFilter注解生效
// @ServletComponentScan
public class SpringbootFilterInterceptorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootFilterInterceptorApplication.class, args);
    }
}
