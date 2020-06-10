package com.cloud.shop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wangfei
 * @date 2020-04-19 16:41
 * 【发送】
 */

@SpringBootApplication
@Slf4j
public class StreamMQMain8801 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMQMain8801.class,args);
        System.out.println("***********StreamMQ8801启动成功**********");
    }
}
