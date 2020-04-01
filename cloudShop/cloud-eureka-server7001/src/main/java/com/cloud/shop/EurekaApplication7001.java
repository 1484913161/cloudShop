package com.cloud.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
 * @author wang fei
 * @date 2020-03-16 10:28
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication7001 {
	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication7001.class, args);
		System.out.println("euraka:7001注册中心启动成功！");
	}


}
