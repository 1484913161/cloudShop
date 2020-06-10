package com.cloud.shop.shop.entity;

import java.io.Serializable;

import com.cloud.shop.shop.enums.ResultEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author wang fei
 * @date 2020-03-16 13:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *   标识码
	 */
	private Integer code;
	
	/**
	 * 返回消息
	 */
    private String message;
    
    /**
             * 返回数据
     */
    private T data;
    
    /**
             * 成功状态
     */
    private boolean isSuccess;
    
//    public CommonResult(Integer code, String message) {
//        this(code, message, null,true);
//    }
    
    /**
     * 
     */
    public CommonResult(ResultEnum resultEnum) {
        if (resultEnum.getCode().equals(ResultEnum.SUCCESS.getCode())) {
            isSuccess = true;
        } else {
            isSuccess = false;
        }
        code = resultEnum.getCode();
        message = resultEnum.getMessage();
    }
    
    public CommonResult(ResultEnum resultEnum, String detail) {
        if (resultEnum.getCode().equals(ResultEnum.SUCCESS.getCode())) {
            isSuccess = true;
        } else {
            isSuccess = false;
        }
        code = resultEnum.getCode();
        message = resultEnum.getMessage() + " : " + detail;
    }
    
    public CommonResult(T t) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.message = ResultEnum.SUCCESS.getMessage();
        this.data = t;
        this.isSuccess = true;
    }
    
    public CommonResult(String messages) {
        code = ResultEnum.FAIL.getCode();
        message = messages;
    }
    
    /**
               * 操作失败
     *
     * @return
     */
    public static CommonResult error() {
        return new CommonResult(ResultEnum.ERROR);
    }
    
    /**
              * 自定义异常消息
     */
    public static CommonResult fail(String message) {
        return new CommonResult(message);
    }
    
    /**
     * 操作失败 默认201
     *
     * @return
     */
    public static CommonResult fail() {
        return new CommonResult(ResultEnum.FAIL);
    }

    public static CommonResult faultTolerant() {
        return new CommonResult(ResultEnum.FAULT_TOLERANT);
    }
    
    /**
     * 无数据
     */
    public static CommonResult noData() {
        return new CommonResult(ResultEnum.NO_DATA);
    }
    

    /**
                * 操作成功带参数
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> CommonResult success(T t) {
        return new CommonResult(t);
    }
    
    /**
              * 操作成功不带参数
     *
     * @return
     */
    public static CommonResult success() {
        return new CommonResult(ResultEnum.SUCCESS);
    }
}
