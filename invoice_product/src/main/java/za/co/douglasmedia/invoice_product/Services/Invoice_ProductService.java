package za.co.douglasmedia.invoice_product.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.douglasmedia.invoice_product.Entities.Invoice_Product;
import za.co.douglasmedia.invoice_product.Repositories.Invoice_ProductRepository;
import za.co.douglasmedia.invoice_product.Utils.DuplicateProductException;
import za.co.douglasmedia.invoice_product.Utils.InvoiceProductNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class Invoice_ProductService {
    private final Invoice_ProductRepository invoice_productRepository;

    @Autowired
    public Invoice_ProductService(Invoice_ProductRepository invoice_productRepository) {
        this.invoice_productRepository = invoice_productRepository;
    }

    // add invoice_product
    public void addInvoiceProduct(Invoice_Product invoice_product) {
        Optional<Invoice_Product> invoiceProduct = invoice_productRepository.findInvoice_ProductByInvoiceIdAndProductId(invoice_product.getInvoiceId(), invoice_product.getProductId());
        if(invoiceProduct.isPresent()) {
            throw new DuplicateProductException("Product has already been added in the invoice");
        } else {
            invoice_productRepository.save(invoice_product);
        }
    }

    // get invoice_product by invoiceId
    public Optional<List<Invoice_Product>> getInvoiceProductByInvoiceId(long invoiceId) {
        Optional<List<Invoice_Product>> invoiceProductByInvoiceId = invoice_productRepository.findInvoice_ProductsByInvoiceId(invoiceId);
        if (invoiceProductByInvoiceId.isEmpty() || invoiceProductByInvoiceId.get().isEmpty()) {
            throw new InvoiceProductNotFoundException("invoice_Product with invoice Id " + invoiceId + " Does not exist");
        } else {
            return  invoiceProductByInvoiceId;
        }
    }

    // Delete product from invoice
    public void removeProductFromInvoice(long invoiceId, long productId) {
        Optional<Invoice_Product> getInvoiceProductToDelete = invoice_productRepository.findInvoice_ProductByInvoiceIdAndProductId(invoiceId, productId);
        if (getInvoiceProductToDelete.isEmpty()){
            throw new InvoiceProductNotFoundException("Product in invoice " + invoiceId + " Not Found");
        } else {
            invoice_productRepository.delete(getInvoiceProductToDelete.get());
        }
    }

}
