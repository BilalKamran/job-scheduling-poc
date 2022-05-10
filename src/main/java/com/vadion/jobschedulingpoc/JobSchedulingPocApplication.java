package com.vadion.jobschedulingpoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@EnableScheduling
public class JobSchedulingPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobSchedulingPocApplication.class, args);
    }

}
