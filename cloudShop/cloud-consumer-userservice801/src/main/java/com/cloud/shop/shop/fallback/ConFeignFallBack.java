package com.cloud.shop.shop.fallback;

import com.cloud.shop.shop.entity.CommonResult;
import com.cloud.shop.shop.entity.User;
import com.cloud.shop.shop.openfeign.ConFeign;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class ConFeignFallBack implements ConFeign {
	
	public CommonResult<User> getUserById(@PathVariable("id") Long id){
		return null;
	}
}
