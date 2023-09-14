package com.douglasmedia.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

public class CorsFilter implements GatewayFilterFactory<Object> {
    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH, OPTIONS");
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
            exchange.getResponse().getHeaders().add("Access-Control-Max-Age", "3600");

            if (HttpMethod.OPTIONS.equals(exchange.getRequest().getMethod())) {
                exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.OK);
                return Mono.empty();
            }
            return chain.filter(exchange);
        };
    }

    @Override
    public Object newConfig() {
        return new Object();
    }
}
