package za.co.douglasmedia.customer.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.douglasmedia.customer.Entities.Customer;

import java.util.List;
import java.util.Optional;


@Repository // responsible for database and it will work with our service
public interface CustomerRepository extends JpaRepository<Customer, Long> { // Needs type and id type
    // Custom CRUD operations
    @Query("SELECT c FROM Customer c WHERE c.userId = ?1 AND c.email = ?2 AND c.deleted = false")
    Optional<Customer> findCustomerByEmailAndNotDeleted(String userId, String email);

    @Query("SELECT c FROM Customer c WHERE c.userId = ?1 AND c.customerId = ?2 AND c.deleted = false")
    Optional<Customer> findCustomerByIdAndNotDeleted(String userId, long id);
    @Query("SELECT c FROM Customer c Where c.userId = ?1 AND c.deleted = false")
    Optional<List<Customer>> findAllCustomersNotDeleted(String userId);

}
