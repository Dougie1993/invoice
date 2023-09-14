package za.co.douglasmedia.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //add this line to register on eureka
@ComponentScan(basePackages = {
		"za.co.douglasmedia.profile.Controllers",
		"za.co.douglasmedia.profile.Entities",
		"za.co.douglasmedia.profile.Repositories",
		"za.co.douglasmedia.profile.Services",
		"za.co.douglasmedia.profile.Utils"
})
public class ProfileApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfileApplication.class, args);
	}

}
