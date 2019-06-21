package com.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: crawler
 * @description: Application
 * @author: wenlongzhou
 * @create: 2019-06-20 19:49
 **/

@SpringBootApplication
@EnableScheduling //开启定时任务
public class Application2 {

    public static void main(String[] args) {
        SpringApplication.run(Application2.class, args);
    }

}
