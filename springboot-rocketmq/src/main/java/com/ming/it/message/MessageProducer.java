package com.ming.it.message;

import com.ming.it.constant.MessageConstant;
import com.ming.it.dto.MessageDTO;
import com.ming.it.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 11119638
 * @date 2021/6/7 15:17
 */
@Component
@Slf4j
public class MessageProducer implements InitializingBean, DisposableBean {

    private final DefaultMQProducer producer = new DefaultMQProducer(MessageConstant.MSG_PRODUCER_GROUP);

    @Value("${rocketmq.name-server}")
    private String nameSrvAddr;

    @Value("${rocketmq.topic:devops_test_rocketmq_topic}")
    private String topic;

    @Value("${rocketmq.producer.retry-times-async:2}")
    private int retryTimesWhenSendAsyncFailed;

    @Value("${rocketmq.producer.send-msg-timeout:3000}")
    private int sendMsgTimeout;

    @Override
    public void afterPropertiesSet() {
        // 地址列表
        producer.setNamesrvAddr(nameSrvAddr);
        // 实例名称
        producer.setInstanceName(MessageConstant.MSG_PRODUCER_INSTANCE_NAME);
        // 异步发送失败，重试次数
        producer.setRetryTimesWhenSendAsyncFailed(retryTimesWhenSendAsyncFailed);
        // 发送消息超时时间，单位毫秒
        producer.setSendMsgTimeout(sendMsgTimeout);
        try {
            log.info("启动生产者");
            producer.start();
        } catch (MQClientException e) {
            log.error("生产者启动失败, 错误信息: {}", e.getErrorMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void sendMsgAsync(MessageDTO messageDTO) {
        final String data = JsonUtil.object2Json(messageDTO);
        try {
            final byte[] messageBody = data.getBytes(RemotingHelper.DEFAULT_CHARSET);
            producer.send(new Message(topic, MessageConstant.MSG_TAG, messageBody), new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("发送消息成功, topic: {}, content: {}, sendResult: {}", topic, data, sendResult.toString());
                }
                @Override
                public void onException(Throwable e) {
                    log.error("发送消息出错, topic: {}, content: {}, exception: {}", topic, data, e.getMessage());
                }
            });
        } catch (Exception e) {
            log.error("发送消息异常, topic: {}, content: {}, exception: {}", topic, data, e.getMessage());
        }
    }

    @Override
    public void destroy() {
        log.info("关闭生产者");
        producer.shutdown();
    }
}
