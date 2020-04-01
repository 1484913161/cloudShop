package com.cloud.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication7002 {
	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication7002.class, args);
		System.out.println("euraka:7002注册中心启动成功！");
	}

}
