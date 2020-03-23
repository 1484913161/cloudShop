package com.cloud.shop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wang fei
 * @date 2020-03-16 11:43
 */
@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication8002 {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication8002.class, args);
        System.out.println("客户端8002生产者启动成功！！！");
    }
}
