package com.cloud.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wangfei
 * @date 2020-03-31 22:00
 */

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
public class OrderHystrixMain801 {
	public static void main(String[] args) {
		SpringApplication.run(OrderHystrixMain801.class);
		System.out.println("Hystrix801消费者启动成功！！！");
	}
}
