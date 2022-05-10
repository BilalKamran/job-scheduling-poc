package com.vadion.jobschedulingpoc.jobrunr;

import lombok.extern.slf4j.Slf4j;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
@Slf4j
public class JobRunrJob {

    static int count = 0;

    @Autowired
    private JobScheduler jobScheduler;

    @PostConstruct
    public void scheduleJob() {
        jobScheduler.scheduleRecurrently(
            "jobrunr-job",
            "0 * * * * *",
            () -> printLog()
        );
    }

    public void printLog() {
        log.info("JobRunr C: {}, T: {}", ++count, new Date());
    }
}
