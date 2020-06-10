package com.cloud.shop.shop.enums;



public enum ResultEnum {

	SUCCESS(200,"成功"),	

	ERROR(500,"网络失败,请重试！"),
	
    SYS_ERROR(505, "系统错误"),

    FAULT_TOLERANT(501, "网络链接失败，请稍后重试"),
  
    FAIL(201, "操作失败，请稍后重试"),
	
	NO_DATA(204, "无数据"),;
	
	private Integer code;
    private String message;
	
    private ResultEnum(Integer code,String message) {
    	this.code = code;
    	this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
