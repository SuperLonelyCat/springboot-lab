package com.ming.it.timer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @author 11119638
 * @date 2021/6/26 18:11
 */
// SpringBoot自带定时器: 工程一启动，定时任务开始执行
@EnableScheduling
@Component
@Slf4j
public class ScheduledTask {

    @Scheduled(cron = "0/5 * * * * *")
    public void task() {
        log.warn("ScheduledTask | current time: {}", new Timestamp(System.currentTimeMillis()));
    }
}
