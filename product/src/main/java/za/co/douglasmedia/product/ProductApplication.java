package za.co.douglasmedia.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EnableDiscoveryClient //add this line to register on eureka
@ComponentScan(basePackages = {
		"za.co.douglasmedia.product.Controllers",
		"za.co.douglasmedia.product.Services",
		"za.co.douglasmedia.product.Repositories",
		"za.co.douglasmedia.product.Entities",
		"za.co.douglasmedia.product.Utils"
})
public class ProductApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
		System.out.println("Product Service is running");
	}
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*") // will set my front-end origin here (e.g., http://localhost:4200)
				.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
				.allowedHeaders("*");
	}

}
