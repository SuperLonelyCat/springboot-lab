package com.ming.it.message;

import com.ming.it.constant.MessageConstant;
import com.ming.it.dto.MessageDTO;
import com.ming.it.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author 11119638
 * @date 2021/6/18 14:05
 */
@Component
@Slf4j
public class MessageConsumer implements InitializingBean, DisposableBean, MessageListenerConcurrently {

    private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MessageConstant.MSG_CONSUMER_GROUP);

    @Value("${rocketmq.name-server}")
    private String nameSrvAddr;

    @Value("${rocketmq.topic:devops_test_rocketmq_topic}")
    private String topic;

    @Value("${rocketmq.consumer.consume-thread-number:20}")
    private int consumeThreadNumber;

    @Value("${rocketmq.consumer.consume-retry-times:2}")
    private int consumeRetryTimes;

    @Override
    public void afterPropertiesSet() {
        // 地址列表
        consumer.setNamesrvAddr(nameSrvAddr);
        // 实例名称
        consumer.setInstanceName(MessageConstant.MSG_CONSUMER_INSTANCE_NAME);
        // 设置从上次消费的位置开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        // 集群消费模式
        consumer.setMessageModel(MessageModel.CLUSTERING);
        // 注册消息监听器
        consumer.registerMessageListener(this);
        // 设置最小消费线程数
        consumer.setConsumeThreadMin(consumeThreadNumber);
        // 设置最大消费线程数，最大与最小线程数应保持一致
        consumer.setConsumeThreadMax(consumeThreadNumber);
        // 单个线程消费消息数量
        consumer.setConsumeMessageBatchMaxSize(MessageConstant.CONSUME_MSG_BATCH_MAX_SIZE);
        // 启动消费端
        try {
            // 订阅话题
            consumer.subscribe(topic, MessageConstant.MSG_TAG);
            log.info("启动消费者");
            consumer.start();
        } catch (MQClientException e) {
            log.error("消费者启动失败, 错误信息: {}", e.getErrorMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        if (CollectionUtils.isEmpty(msgs) || msgs.size() > 1) {
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
        final MessageExt msg = msgs.get(0);
        log.info("接收信息成功, topic: {}, id: {}, msg: {}", msg.getTopic(), msg.getMsgId(), msg);
        String msgBody;
        try {
            msgBody = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
            final MessageDTO messageDTO = JsonUtil.json2Object(msgBody, MessageDTO.class);
            log.info("MessageDTO - {}", messageDTO);
            log.info("消费消息成功, topic: {}, id: {}, content: {}", msg.getTopic(), msg.getMsgId(), msgBody);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        } catch (Exception e) {
            log.error("消费消息异常, topic: {}, id: {}, exception: {}", msg.getTopic(), msg.getMsgId(),
                    ExceptionUtils.getStackTrace(e));
            if (consumeRetryTimes == msg.getReconsumeTimes()) {
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
    }

    @Override
    public void destroy() {
        log.info("关闭消费者");
        consumer.shutdown();
    }
}
