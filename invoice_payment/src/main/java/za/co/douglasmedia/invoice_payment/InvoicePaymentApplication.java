package za.co.douglasmedia.invoice_payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {
		"za.co.douglasmedia.invoice_payment.Controllers",
		"za.co.douglasmedia.invoice_payment.Services",
		"za.co.douglasmedia.invoice_payment.Repositories",
		"za.co.douglasmedia.invoice_payment.Entities",
		"za.co.douglasmedia.invoice_payment.Utils"
})
public class InvoicePaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoicePaymentApplication.class, args);
		System.out.println("Invoice_Payment Service Running");
	}

}
