package com.vadion.jobschedulingpoc.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class SpringScheduler {

    static int count = 0;

    @Scheduled(cron = "0 * * * * *")
    public void scheduleJob() {
        log.info("Spring C: {}, T: {}", ++count, new Date());
    }
}
