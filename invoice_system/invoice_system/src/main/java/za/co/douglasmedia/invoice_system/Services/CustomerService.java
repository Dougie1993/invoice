package za.co.douglasmedia.invoice_system.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.douglasmedia.invoice_system.Entities.Customer;
import za.co.douglasmedia.invoice_system.Repositories.CustomerRepository;
import za.co.douglasmedia.invoice_system.Utils.DuplicateEmailException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service // Let spring know about this bean for dependency injection
public class CustomerService {
    // Business logic class

    private final CustomerRepository customerRepository;

    @Autowired // dependency injection
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public void addCustomer(Customer customer) {
        Optional<Customer> customerByEmail = customerRepository.findCustomerByEmail(customer.getEmail());
        if(customerByEmail.isPresent()) {
            throw new DuplicateEmailException("Customer with email " + customer.getEmail() + " already exists");
        } else {
            customerRepository.save(customer);
        }


    }
}
