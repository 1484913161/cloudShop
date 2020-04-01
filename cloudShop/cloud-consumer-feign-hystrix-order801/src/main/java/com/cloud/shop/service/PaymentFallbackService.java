package com.cloud.shop.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{

	@Override
	public String paymentInfo_OK(Integer id) {
		// TODO Auto-generated method stub
		return "paymentInfo_OK----------------fallback";
	}

	@Override
	public String paymentInfo_TimeOut(Integer id) {
		// TODO Auto-generated method stub
		return "paymentInfo_TimeOut---------fallback";
	}

	


}
