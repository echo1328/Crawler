package com.job.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @program: crawler
 * @description: TaskTest
 * @author: wenlongzhou
 * @create: 2019-06-21 21:57
 **/

@Component
public class TaskTest {

    //@Scheduled(cron = "0/5 * * * * *")
    public void test() {
        System.out.println("定时任务执行了");
    }

}
