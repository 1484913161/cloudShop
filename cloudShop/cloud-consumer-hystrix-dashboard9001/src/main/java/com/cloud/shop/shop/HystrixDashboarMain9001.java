package com.cloud.shop.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author wangfei
 * @date 2020-04-02 23:13
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboarMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboarMain9001.class);
        System.out.println("hystrixDashboar9001启动成功！！！");
    }
}
