package com.cloud.shop.dao;


import com.cloud.shop.entity.CommonResult;
import com.cloud.shop.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wang fei
 * @date 2020-03-16 13:42
 */
@Mapper
public interface UserDao {
    /**
     * 添加用户
     */
    int create(User user);

    /**
     *根据Id查询
     */
    User getUserById(@Param("id") Long id);
}
