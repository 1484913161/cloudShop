package com.cloud.shop.shop.service;

import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author wangfei
 * @date 2020-03-31 21:20
 */
@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return "线程池:" + Thread.currentThread().getName()+ "paymentInfo_OK,id=" + id +"\t" + "O(∩_∩)O";
    }
    
    
    /***
     * @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
     * 
           * 【意思：这个接口只能请求3秒钟。】三秒内走正常程序，超过3秒走容错。
           * 
           *  @HystrixCommand中如果更该属性如：value=3000    项目是热部署，建议重启项目。热部署对于@HystrixCommand内部属性不太支持
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHander",commandProperties = {
    		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id){
    	int age = 10/0;
//        int timeNumber = 3;
//        try {
//            TimeUnit.SECONDS.sleep(timeNumber);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "线程池:" + Thread.currentThread().getName()+ "paymentInfo_OK,id=" + id +"\t" + "┭┮﹏┭┮" ;
    }
    
    public String paymentInfo_TimeOutHander(Integer id) {
    	
    	return "线程池:" + Thread.currentThread().getName()+ "系统繁忙或则运行报错，请稍后再试,id=" + id +"\t" + "┭┮﹏┭┮";
    }
    
    //===================服务熔断======================================
    
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallBack",commandProperties = {
    		@HystrixProperty(name  = "circuitBreaker.enabled",value = "true"),//是否开启断路器
    		@HystrixProperty(name  = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
    		@HystrixProperty(name  = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//【10秒】事件窗口期
    		@HystrixProperty(name  = "circuitBreaker.errorThresholdPercentage",value = "60"),//【此处是60%】失败率达到多少后跳匝
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
    	if(id<0) {
    		throw new RuntimeException("id不能为负数");
    	}
    	String serialNumber = UUID.randomUUID().toString();
    	return  Thread.currentThread().getName() + "\t" + "调用成功，流水号" + serialNumber;
    }
    
    public String paymentCircuitBreaker_fallBack(@PathVariable("id") Integer id) {
    	
    	return  "id不能为负数,请稍后再试！！！";
    }
}
