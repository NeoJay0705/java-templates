package com.example.templates.schedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.templates.aop.AspectAnnotation;

@Configuration
@EnableScheduling
@EnableAsync
public class ScheduleConfig {
    // @Scheduled(fixedDelay = 1000)
    @AspectAnnotation
    public void scheduleFixedDelayTask() {
        System.out.println(
        "Fixed delay task - " + System.currentTimeMillis() / 1000);
    }

    @Async
    // https://www.freeformatter.com/cron-expression-generator-quartz.html
    // @Scheduled(cron = "0 15 10 15 * ?", zone = "Europe/Paris")
    // @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        System.out.println(
          "Fixed rate task async - " + System.currentTimeMillis() / 1000);
        Thread.sleep(2000);
    }
}
