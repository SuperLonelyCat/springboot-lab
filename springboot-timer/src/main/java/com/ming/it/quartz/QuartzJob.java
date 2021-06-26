package com.ming.it.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @author 11119638
 * @date 2021/3/8 10:40
 */
@Slf4j
public class QuartzJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.warn("QuartzTask | current time: {}", new Timestamp(System.currentTimeMillis()));
    }
}
