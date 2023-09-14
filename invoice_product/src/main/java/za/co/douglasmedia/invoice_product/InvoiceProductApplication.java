package za.co.douglasmedia.invoice_product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {
		"za.co.douglasmedia.invoice_product.Controllers",
		"za.co.douglasmedia.invoice_product.Services",
		"za.co.douglasmedia.invoice_product.Repositories",
		"za.co.douglasmedia.invoice_product.Entities",
		"za.co.douglasmedia.invoice_product.Utils"
})
public class InvoiceProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceProductApplication.class, args);
		System.out.println("Invoice_Product Service Started");
	}

}
