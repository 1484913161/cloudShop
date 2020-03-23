package com.cloud.shop.service.impl;

import com.cloud.shop.dao.UserDao;
import com.cloud.shop.entity.CommonResult;
import com.cloud.shop.entity.User;
import com.cloud.shop.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wang fei
 * @date 2020-03-16 13:49
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public int create(User user) {
        return userDao.create(user);
    }

    @Override
    public CommonResult<User> getUserById(Long id) {
    	User user = userDao.getUserById(id);
    	if(user != null) {
    		return CommonResult.success(user);
    	}else {
    		return CommonResult.error();
    	}
    }
}
