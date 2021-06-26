package com.ming.it.controller;

import com.ming.it.quartz.QuartzTimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 11119638
 * @date 2021/6/26 18:27
 */
@RestController
public class TimerController {

    @Autowired
    private QuartzTimerTask quartzTimerTask;

    @GetMapping("/start")
    public String startTask() {
        quartzTimerTask.startScheduleJob();
        return "good";
    }
}
