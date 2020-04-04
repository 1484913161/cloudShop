package com.clud.shop.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GetWayFilter implements GlobalFilter,Ordered{

	@Override
	public int getOrder() {
		//优先级【越小优先级越高】
		return 0;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		log.info("**********come in GetWayFilter");
		String name = exchange.getRequest().getQueryParams().getFirst("name");
		if(name == null) {
			log.info("********用户名为null,不合法啊。┭┮﹏┭┮");
			exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
			return exchange.getResponse().setComplete();
		}
		return chain.filter(exchange);
	}

}
