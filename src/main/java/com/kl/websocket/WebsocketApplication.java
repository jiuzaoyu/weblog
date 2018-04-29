package com.kl.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
@RestController
public class WebsocketApplication {
    private Logger logger = LoggerFactory.getLogger(WebsocketApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebsocketApplication.class, args);
    }

    int info = 1;

    @Scheduled(fixedRate = 1000)
    public void outputLogger() {
        logger.info("测试日志输出" + info++);
        throw new RuntimeException();
    }

    @RequestMapping("/hello")
    public String hello() {
        logger.debug("访问了hello");
        return "hello!";
    }
}
