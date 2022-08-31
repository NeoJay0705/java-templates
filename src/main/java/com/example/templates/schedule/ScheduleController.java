package com.example.templates.schedule;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sch")
@EnableAsync
public class ScheduleController {

    @RequestMapping("one")
    // @Scheduled(fixedDelay = 1000)
    public Object find1() throws InterruptedException {
        // List<Object> result = new ArrayList<>();
        // this.userRepos.findAll().forEach(result::add);
        System.out.println("oneoneone");
        Thread.sleep(2000);
        System.out.println("one - end");
        return "qqq";
    }

    @RequestMapping("two")
    @Async
    // @Scheduled(fixedRate = 1000)
    public Object find2() throws InterruptedException {
        // List<Object> result = new ArrayList<>();
        // this.userRepos.findAll().forEach(result::add);
        System.out.println("two");
        Thread.sleep(2000);
        System.out.println("two - end");
        return "qqq";
    }
}
