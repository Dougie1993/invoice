package za.co.douglasmedia.invoice_payment.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import za.co.douglasmedia.invoice_payment.Entities.Invoice_Payment;

import java.util.List;
import java.util.Optional;

public interface Invoice_PaymentRepository extends JpaRepository <Invoice_Payment, Long>{
    @Query("SELECT ip FROM Invoice_Payment ip Where ip.invoiceId = ?1")
    Optional<List<Invoice_Payment>> findAllPaymentsForInvoice(long invoiceId);

}
