package com.ming.it.service;

import com.ming.it.api.ProducerApi;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 11119638
 * @date 2021/4/23 17:53
 */
@DubboService
public class ProducerService implements ProducerApi {

    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

    @Override
    public String produce(String msg) {

        final String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        final String producer = now + " send msg: " + msg + " request from consumer: " +
                RpcContext.getContext().getRemoteAddress();
        logger.warn(producer);
        return producer;
    }
}
