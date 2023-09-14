package za.co.douglasmedia.invoice_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.douglasmedia.invoice_system.Entities.Customer;

import java.util.Optional;

@Repository // responsible for database and it will work with our service
public interface CustomerRepository extends JpaRepository<Customer, Long> { // Needs type and id type
    // Custom CRUD operations
    @Query ("SELECT c FROM Customer c WHERE c.email = ?1")
    Optional<Customer> findCustomerByEmail(String email);

}
