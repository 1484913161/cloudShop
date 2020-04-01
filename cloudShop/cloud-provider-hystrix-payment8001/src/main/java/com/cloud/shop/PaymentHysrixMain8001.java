package com.cloud.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wangfei
 * @date 2020-03-31 21:18
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class PaymentHysrixMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHysrixMain8001.class);
        System.out.println("hystrix8001启动成功！！！");
    }
}
