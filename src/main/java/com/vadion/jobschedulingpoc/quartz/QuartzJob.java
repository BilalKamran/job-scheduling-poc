package com.vadion.jobschedulingpoc.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class QuartzJob implements Job {

    static int count = 0;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("Quartz C: {}, T: {}", ++count, new Date());
    }
}
