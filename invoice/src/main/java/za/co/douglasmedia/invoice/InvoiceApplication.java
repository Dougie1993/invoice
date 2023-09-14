package za.co.douglasmedia.invoice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableDiscoveryClient //add this line to register on eureka
@ComponentScan(basePackages = {
		"za.co.douglasmedia.invoice.Controllers",
		"za.co.douglasmedia.invoice.Entities",
		"za.co.douglasmedia.invoice.Repositories",
		"za.co.douglasmedia.invoice.Services",
		"za.co.douglasmedia.invoice.Utils"
})
public class InvoiceApplication implements WebMvcConfigurer{

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(InvoiceApplication.class, args);
		System.out.println("Invoice Service Running");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*") // will set my front-end origin here (e.g., http://localhost:4200)
				.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
				.allowedHeaders("*");
	}

}
