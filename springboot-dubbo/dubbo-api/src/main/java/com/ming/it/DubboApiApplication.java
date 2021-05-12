package com.ming.it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 删除启动类, 无法使用mvn clean package发布到本地仓库
@SpringBootApplication
public class DubboApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboApiApplication.class, args);
	}
}
