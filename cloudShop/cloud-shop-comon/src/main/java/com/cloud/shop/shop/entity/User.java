package com.cloud.shop.shop.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wang fei
 * @date 2020-03-16 13:41
 */
@Data
public class User implements Serializable {
    private Long id;
    private String userName;
}
