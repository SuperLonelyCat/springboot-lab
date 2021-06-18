package com.ming.it.constant;

/**
 * @author 11119638
 * @date 2021/6/18 14:22
 */
public interface MessageConstant {

    /** 生产者组名称 */
    String MSG_PRODUCER_GROUP = "rocketmq_producer_group";

    /** 消费者组名称 */
    String MSG_CONSUMER_GROUP = "rocketmq_consumer_group";

    /** 生产者实例名称 */
    String MSG_PRODUCER_INSTANCE_NAME = "RocketMqProducer";

    /** 消费者实例名称 */
    String MSG_CONSUMER_INSTANCE_NAME = "RocketMqConsumer";

    /** 消息标签 */
    String MSG_TAG = "*";

    /** 单个线程消费消息数量 */
    int CONSUME_MSG_BATCH_MAX_SIZE = 1;
}
