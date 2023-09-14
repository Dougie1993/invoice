package za.co.douglasmedia.invoice_system.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class InvoiceController {
    public String sayHello(){
        return "Hello Invoice System";
    }
}
