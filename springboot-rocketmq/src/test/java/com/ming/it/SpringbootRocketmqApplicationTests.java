package com.ming.it;

import com.ming.it.dto.MessageDTO;
import com.ming.it.message.MessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
class SpringbootRocketmqApplicationTests {

    @Autowired
    private MessageProducer messageProducer;

    @Test
    void contextLoads() throws InterruptedException {
        messageProducer.sendMsgAsync(MessageDTO.builder()
                .id(UUID.randomUUID().toString())
                .name("fanglm")
                .accountLife(2)
                .accountBalance(new BigDecimal("123.456"))
                .createdTime(LocalDateTime.now())
                .updatedTime(new Date())
                .build());
        TimeUnit.MINUTES.sleep(5);
    }
}
