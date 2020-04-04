package com.clud.shop.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetWayConfig {
	/****
	 * 外部配置routes
	 * @param routeLocatorBuilder
	 * @return
	 * 
	 * 访问http://localhost:9527/guonei路径。
	 */
	@Bean
	public RouteLocator customerLocator(RouteLocatorBuilder routeLocatorBuilder) {
		Builder routes = routeLocatorBuilder.routes();
		routes.route("path_route_test",
				r ->r.path("/guonei")
				.uri("http://news.baidu.com/guonei")).build();
		return routes.build();
	}
}
