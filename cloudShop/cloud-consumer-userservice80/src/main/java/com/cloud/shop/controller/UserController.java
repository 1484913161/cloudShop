package com.cloud.shop.controller;

import com.cloud.shop.entity.CommonResult;
import com.cloud.shop.entity.User;
import com.cloud.shop.openfeign.ConFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import javax.annotation.Resource;

/**
 * @author wang fei
 * @date 2020-03-16 20:11
 */
@RestController
@Slf4j
public class UserController {
    //private final static String URL = "http://localhost:8001";//不集群
//    private static final String BASEURL = "http://CLOUD-PROVIDE-USERSERVICE";//集群
//
//    @Resource
//    private RestTemplate restTemplate;
//
//
//    @GetMapping("/consumer/get/{id}")
//    public CommonResult<User> getUserById(@PathVariable("id") Long id) {
//        return restTemplate.getForObject(BASEURL + "/user/get/" + id, CommonResult.class, id);
//    }
//
//    @GetMapping("/consumer/create")
//    public CommonResult<User> create(User user) {
//        return restTemplate.postForObject(BASEURL + "/user/create", user, CommonResult.class);
//    }
	
	@Autowired
	private ConFeign conFeign;
	
	@GetMapping("/consumer/get/{id}")
	public CommonResult<User> getUserById(@PathVariable("id") Long id) {
		
      return conFeign.getUserById(id);
  }
	

}
