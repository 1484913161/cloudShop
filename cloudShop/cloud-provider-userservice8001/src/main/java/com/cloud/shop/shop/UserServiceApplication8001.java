package com.cloud.shop.shop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wang fei
 * @date 2020-03-16 11:43
 */
@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication8001 {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication8001.class, args);
        System.out.println("客户端8001生产者启动成功！！！");
    }
}
