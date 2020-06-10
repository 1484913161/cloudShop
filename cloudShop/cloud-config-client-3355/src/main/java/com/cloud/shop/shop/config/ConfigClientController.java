package com.cloud.shop.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/***
 * @RefreshScope  刷新功能
 * @author Administrator
 *
 */
@RestController
@RefreshScope
public class ConfigClientController {
	
	@Value("${bood.codl}")
	private String configInfo;
	
	@GetMapping("/configInfo")
	public String getConfigInfo() {
		return configInfo;
	}
}
