package za.co.douglasmedia.invoice_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// important configuration
@ComponentScan(basePackages = {"za.co.douglasmedia.invoice_system.Controllers", "za.co.douglasmedia.invoice_system.Services", "za.co.douglasmedia.invoice_system.Repositories"})
public class InvoiceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceSystemApplication.class, args);
		System.out.println("Invoice system running");
	}

}
