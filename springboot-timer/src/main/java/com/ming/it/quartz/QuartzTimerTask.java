package com.ming.it.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

/**
 * @author 11119638
 * @date 2021/6/26 18:24
 */
// Quartz第三方定时器: 可选择执行时机、终止时机以及执行次数
@Component
@Slf4j
public class QuartzTimerTask {

    public void startScheduleJob() {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            // 具体任务
            JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class).build();
            // 触发时间点 - 周期: 20秒，执行次数: 2
            SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder
                    .simpleSchedule()
                    .withIntervalInSeconds(20)
                    .withRepeatCount(2);
            Trigger trigger = TriggerBuilder.newTrigger()
                    .startNow()
                    .withSchedule(simpleScheduleBuilder).build();
            // 交由Scheduler安排触发
            scheduler.scheduleJob(jobDetail,trigger);
        } catch (SchedulerException e) {
            log.error("QuartzTimerTask | startScheduleJob - {}", e.getMessage());
        }
    }
}
