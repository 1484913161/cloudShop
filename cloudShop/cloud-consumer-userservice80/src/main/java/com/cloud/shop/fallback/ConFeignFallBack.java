package com.cloud.shop.fallback;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.cloud.shop.entity.CommonResult;
import com.cloud.shop.entity.User;
import com.cloud.shop.openfeign.ConFeign;

@Component
public class ConFeignFallBack implements ConFeign{
	
	public CommonResult<User> getUserById(@PathVariable("id") Long id){
		
		return null;
	}
}
