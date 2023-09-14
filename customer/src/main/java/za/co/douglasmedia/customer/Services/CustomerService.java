package za.co.douglasmedia.customer.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.douglasmedia.customer.Entities.Customer;
import za.co.douglasmedia.customer.Repositories.CustomerRepository;
import za.co.douglasmedia.customer.Utils.CustomerNotFoundException;
import za.co.douglasmedia.customer.Utils.DuplicateEmailException;

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

    // this will be used by Super admin to get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // get customers
    public Optional<List<Customer>> getCustomers(String userId) {
        Optional<List<Customer>> customers = customerRepository.findAllCustomersNotDeleted(userId);
        if(!customers.isPresent() || customers.get().isEmpty()) {
            throw new CustomerNotFoundException("No Customers saved");
        } else {
            return customers;
        }

    }

    // Get Customer by Id
    public Optional<Customer> getCustomer(String userId, long id){
        Optional<Customer> customerById = customerRepository.findCustomerByIdAndNotDeleted(userId, id);
        if(!customerById.isPresent()) {
            throw new CustomerNotFoundException("Customer requested not found");
        } else {
            return customerById;
        }

    }
    // Get by email NB* email has to be unique for each customer/business
    public Optional<Customer> getCustomerByEmail(String userId, String email){
        Optional<Customer> customerByEmail = customerRepository.findCustomerByEmailAndNotDeleted(userId,email);
        if(!customerByEmail.isPresent()) {
            throw new CustomerNotFoundException("Customer requested not found");
        } else {
            return customerByEmail;
        }
    }

    // Add new Customer with unique email
    public void addCustomer(String userId, Customer customer) {
        Optional<Customer> customerByEmail = customerRepository.findCustomerByEmailAndNotDeleted(userId, customer.getEmail());
        if(customerByEmail.isPresent()) {
            throw new DuplicateEmailException("Customer with email " + customer.getEmail() + " already exists");
        } else {
            customerRepository.save(customer);
        }

    }

    // Update customer by id
    public void updateCustomer(String userId, Customer customer) {
        if (customer.getCustomerId() != null) {
            Optional<Customer> updateCustomer = getCustomer(userId, customer.getCustomerId());
            if (updateCustomer.isPresent()) {
                customerRepository.save(customer);
            } else {
                throw new CustomerNotFoundException("Customer to be updated not found");
            }
        } else {
            throw new CustomerNotFoundException("Customer Needs Id");
        }

    }
}
