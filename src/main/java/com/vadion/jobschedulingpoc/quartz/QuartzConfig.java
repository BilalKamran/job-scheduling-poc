package com.vadion.jobschedulingpoc.quartz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;


import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class QuartzConfig {

    private final ApplicationContext applicationContext;

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        log.debug("Configuring Job factory");

        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public Scheduler scheduler(Trigger trigger, JobDetail job, SchedulerFactoryBean factory) throws SchedulerException {
        log.debug("Getting a handle to the Scheduler");
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(job, trigger);

        log.debug("Starting Scheduler threads");
        scheduler.start();
        return scheduler;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(springBeanJobFactory());
        return factory;
    }

    @Bean
    public JobDetail jobDetail() {

        return newJob().ofType(QuartzJob.class).ofType(QuartzJob.class)
            .storeDurably()
            .withIdentity("quartz-job")
            .build();
    }

    @Bean
    public Trigger trigger(JobDetail job) {

        return newTrigger().forJob(job)
            .withIdentity("quartz-trigger")
            .withSchedule(CronScheduleBuilder.cronSchedule("0 * * * * ? *"))
            .build();
    }
}
