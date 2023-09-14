package za.co.douglasmedia.invoice_system.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.douglasmedia.invoice_system.Entities.Customer;
import za.co.douglasmedia.invoice_system.Services.CustomerService;
import za.co.douglasmedia.invoice_system.Utils.CustomerResponse;
import za.co.douglasmedia.invoice_system.Utils.DuplicateEmailException;

import java.util.List;

@RestController
@RequestMapping(path = "customer")
public class CustomerController {
    private final CustomerService customerService;
    @Autowired        // Dependency injection
    public CustomerController(CustomerService customerService) {
        this.customerService =  customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping("/customer")
    public ResponseEntity<CustomerResponse> registerCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);

        CustomerResponse response = new CustomerResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Customer saved");

        return ResponseEntity.ok(response);
    }

    // Exception handlers to format response
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<CustomerResponse> handleDuplicateEmailException(DuplicateEmailException ex) {
        CustomerResponse response = new CustomerResponse();
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

}
