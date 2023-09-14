package com.douglasmedia.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
		System.out.println("Gateway Service is running");
	}
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//				.allowedOrigins("*") // will set my front-end origin here (e.g., http://localhost:4200)
//				.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
//				.allowedHeaders("*");
//	}
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("customer", r -> r.path("/customer/**")
						.filters(f -> f
								.addResponseHeader("Access-Control-Allow-Origin", "*") // Define your allowed origins
								.addResponseHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,PATCH")
								.addResponseHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With"))
						.uri("http://localhost:8081"))
				.route("product", r -> r.path("/product/**")
						.filters(f -> f
								.addResponseHeader("Access-Control-Allow-Origin", "*") // Define your allowed origins
								.addResponseHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,PATCH")
								.addResponseHeader("Access-Control-Allow-Headers", "*"))
						.uri("http://localhost:8082"))
				.route("invoice", r -> r.path("/invoice/**")
						.filters(f -> f
								.addResponseHeader("Access-Control-Allow-Origin", "*") // Define your allowed origins
								.addResponseHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,PATCH")
								.addResponseHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With"))
						.uri("http://localhost:8083"))

				.build();
	}
}
