package za.co.douglasmedia.customer.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.douglasmedia.customer.Entities.Customer;
import za.co.douglasmedia.customer.Services.CustomerService;
import za.co.douglasmedia.customer.Utils.CustomerNotFoundException;
import za.co.douglasmedia.customer.Utils.CustomerResponse;
import za.co.douglasmedia.customer.Utils.DuplicateEmailException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "customer")
//@CrossOrigin
public class CustomerController {
    private final CustomerService customerService;
    @Autowired        // Dependency injection
    public CustomerController(CustomerService customerService) {
        this.customerService =  customerService;
    }

    // Get all customers
    @GetMapping("/customers/{userId}")
    // public List<Customer> getCustomers() {
    //    return customerService.getCustomers();
    //}
    public ResponseEntity<Optional<List<Customer>>> getCustomers(@PathVariable("userId") String userId) {
        Optional<List<Customer>> customers;
        customers = customerService.getCustomers(userId);
        return ResponseEntity.ok(customers);
    }


    // Get customer by id
    @GetMapping("/customer/id/{userId}/{id}")
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable("userId") String userId,
                                                              @PathVariable("id") long id) {
        Optional<Customer> customer;
        customer = customerService.getCustomer(userId, id);
        return ResponseEntity.ok(customer);
    }

    // get customer by email
    @GetMapping("/customer/email/{userId}/{email}")
    public ResponseEntity<Optional<Customer>> getCustomerByEmail(@PathVariable("userId") String userId,
                                                                 @PathVariable("email") String email) {
        Optional<Customer> customer;
        customer = customerService.getCustomerByEmail(userId,email);
        return ResponseEntity.ok(customer);
    }

    // Register new customer
    @PostMapping("/customer/{userId}")
    public ResponseEntity<CustomerResponse> registerCustomer(@PathVariable("userId") String userId,
                                                             @RequestBody Customer customer) {
        customerService.addCustomer(userId, customer);

        CustomerResponse response = new CustomerResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Customer saved");

        return ResponseEntity.ok(response);
    }

    //  update Customer
    @PatchMapping("/customer/{userId}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable("userId") String userId,
                                                           @RequestBody Customer customer) {
        customerService.updateCustomer(userId, customer);
        CustomerResponse response = new CustomerResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Customer updated");

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

    @ExceptionHandler(CustomerNotFoundException.class)
    public  ResponseEntity<CustomerResponse> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        CustomerResponse response = new CustomerResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
