package com.cloud.shop.controller;

import com.cloud.shop.entity.CommonResult;
import com.cloud.shop.entity.User;
import com.cloud.shop.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
    @Resource
    private UserService userService;



    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody User user) {
        int result = userService.create(user);

        log.info("插入结果：" + result);

        if (result > 0) {
            return new CommonResult(200, "插入成功", result, true);
        } else {
            return new CommonResult(444, "插入失败", null, false);
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public CommonResult<User> getPaymenyById(@PathVariable("id")Long id) {   	
    	return userService.getUserById(id);
    }

}