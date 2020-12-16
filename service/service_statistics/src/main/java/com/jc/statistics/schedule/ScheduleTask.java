package com.jc.statistics.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/12/03 00:30
 */
@Component
public class ScheduleTask {

    @Scheduled(cron = "0/5 * * * * ?")
    public void task() {
        System.out.println("schedule is run");

    }
}
