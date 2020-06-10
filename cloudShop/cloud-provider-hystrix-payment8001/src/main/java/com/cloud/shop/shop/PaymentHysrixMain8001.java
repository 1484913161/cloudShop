package com.cloud.shop.shop;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

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

    /**
     * 此配置为了服务监控而部署，与服务容错本身没有关系springcolud升级后的坑
     * ServletRegistrationBean因为springcloud的默认路径不足"/hystrix.stream",
     * 只需要在项目中配置下面的Servlet就可以了
     * */
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
