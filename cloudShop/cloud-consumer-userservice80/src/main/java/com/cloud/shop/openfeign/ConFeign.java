package com.cloud.shop.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.cloud.shop.entity.CommonResult;
import com.cloud.shop.entity.User;
import com.cloud.shop.fallback.ConFeignFallBack;

@Component
@FeignClient(value = "CLOUD-PROVIDE-USERSERVICE" )
public interface ConFeign {
	
	@GetMapping(value = "user/get/{id}")
	CommonResult<User> getUserById(@PathVariable("id") Long id);

}
