package za.co.douglasmedia.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"za.co.douglasmedia.customer.Controllers",
		"za.co.douglasmedia.customer.Services",
		"za.co.douglasmedia.customer.Repositories",
		"za.co.douglasmedia.customer.Entities",
		"za.co.douglasmedia.customer.Utils"})
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
		System.out.println("Customer Service Running");
	}
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//				.allowedOrigins("*") // will set my front-end origin here (e.g., http://localhost:4200)
//				.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
//				.allowedHeaders("*");
//	}

}
