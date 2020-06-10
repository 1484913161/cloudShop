package com.cloud.shop.shop.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.cloud.shop.shop.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;

@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")//全局容错，如果没有指定定义容错内容，默默人走这个
public class OrderHystrixController {
	
	//logger日志
	private static final Logger log = LoggerFactory.getLogger(OrderHystrixController.class);
	
	@Resource
	private PaymentHystrixService paymentHystrixService;
	
	@GetMapping("consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
		String result  = paymentHystrixService.paymentInfo_OK(id);
		log.info(result);
		return result;
	}
	/**
	 * 请求这个接口1.5秒，超过这个事件走fallback【容错】
	 * @param id
	 * @return
	 */
//	@HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
//	    		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//	    })
//	@HystrixCommand
	@GetMapping("consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
		String result  = paymentHystrixService.paymentInfo_TimeOut(id);
		log.info(result);
		return result;
	}
	 
	 public String paymentTimeOutFallbackMethod(@PathVariable("id")Integer id) {
		 return "我是801消费者，对方支付系统繁忙，请十分钟后在试一试!!!";
	 }
	 
	 //下面是全局fallback
	 public String payment_Global_FallbackMethod(){
		 
		 return "Global异常处理信息，请稍后在试一试！！！";
	 } 
}
