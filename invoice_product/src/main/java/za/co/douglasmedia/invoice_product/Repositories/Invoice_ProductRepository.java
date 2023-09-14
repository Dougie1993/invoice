package za.co.douglasmedia.invoice_product.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import za.co.douglasmedia.invoice_product.Entities.Invoice_Product;

import java.util.List;
import java.util.Optional;

public interface Invoice_ProductRepository extends JpaRepository<Invoice_Product, Long> {

    @Query("SELECT ip FROM Invoice_Product ip WHERE ip.invoiceId = ?1")
    Optional<List<Invoice_Product>> findInvoice_ProductsByInvoiceId(long invoiceId);

    // used to delete
    @Query("SELECT ip FROM Invoice_Product ip WHERE ip.invoiceId = ?1 AND ip.productId = ?2")
    Optional<Invoice_Product> findInvoice_ProductByInvoiceIdAndProductId(long invoiceId, long productId);
}
