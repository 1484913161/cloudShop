package com.cloud.shop.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wang fei
 * @date 2020-03-16 20:06
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ConsumerApplication801 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication801.class,args);
        System.out.println("消费者：801启动成功！！！");
    }
}
