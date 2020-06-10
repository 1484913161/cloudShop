package com.cloud.shop.shop.controller;

import com.cloud.shop.shop.entity.CommonResult;
import com.cloud.shop.shop.entity.User;
import com.cloud.shop.shop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @author wang fei
 * @date 2020-03-16 13:52
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
	
	@Value("${server.port}")
	private String serverPort;
	
    @Resource
    private UserService userService;

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody User user) {
        int result = userService.create(user);

        log.info("插入结果：" + result);

        if (result > 0) {
            return CommonResult.success(result);
        } else {
            return CommonResult.fail();
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public CommonResult<User> getPaymenyById(@PathVariable("id")Long id) {   	
    	return userService.getUserById(id);
    }
    
    @RequestMapping(value = "/lb")
    public String lb() {   	
    	return serverPort;
    }

}