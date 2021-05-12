package com.ming.it.controller;

import com.ming.it.api.ProducerApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 11119638
 * @date 2021/4/23 18:29
 */
@RestController
public class ConsumerController {

    @DubboReference
    private ProducerApi producerApi;

    @RequestMapping("/get")
    public String get() {
        return producerApi.produce("Hello FLM!");
    }
}
