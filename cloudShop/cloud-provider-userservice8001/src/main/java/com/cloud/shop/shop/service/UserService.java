package com.cloud.shop.shop.service;

import com.cloud.shop.shop.entity.CommonResult;
import com.cloud.shop.shop.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author wang fei
 * @date 2020-03-16 13:49
 */
public interface UserService {
    /**
     * 添加用户
     */
    int create(User user);

    /**
     *根据Id查询
     */
    CommonResult<User> getUserById(@Param("id") Long id);
}
