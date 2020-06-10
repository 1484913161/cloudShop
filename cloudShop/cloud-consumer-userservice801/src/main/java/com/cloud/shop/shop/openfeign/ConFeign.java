package com.cloud.shop.shop.openfeign;

import com.cloud.shop.shop.entity.CommonResult;
import com.cloud.shop.shop.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDE-USERSERVICE" )
public interface ConFeign {
	
	@GetMapping(value = "user/get/{id}")
    CommonResult<User> getUserById(@PathVariable("id") Long id);

}
