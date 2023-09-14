package za.co.douglasmedia.invoice_product.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.douglasmedia.invoice_product.Entities.Invoice_Product;
import za.co.douglasmedia.invoice_product.Services.Invoice_ProductService;
import za.co.douglasmedia.invoice_product.Utils.DuplicateProductException;
import za.co.douglasmedia.invoice_product.Utils.InvoiceProductNotFoundException;
import za.co.douglasmedia.invoice_product.Utils.InvoiceProductResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "invoice_product")
public class Invoice_ProductController {
    private final Invoice_ProductService invoice_productService;

    @Autowired
    public Invoice_ProductController(Invoice_ProductService invoice_productService) {
        this.invoice_productService = invoice_productService;
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<Optional<List<Invoice_Product>>> getInvoiceProductsByInvoiceId(@PathVariable("invoiceId") long invoiceId) {
        Optional<List<Invoice_Product>> invoiceProducts;
        invoiceProducts = invoice_productService.getInvoiceProductByInvoiceId(invoiceId);
        return ResponseEntity.ok(invoiceProducts);
    }

    @PostMapping
    public ResponseEntity<InvoiceProductResponse> addInvoiceProduct(@RequestBody Invoice_Product invoice_product){
        invoice_productService.addInvoiceProduct(invoice_product);

        InvoiceProductResponse response = new InvoiceProductResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Invoice_Product Saved");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{invoiceId}/{productId}")
    public ResponseEntity<InvoiceProductResponse> removeProductFromInvoice(@PathVariable long invoiceId, @PathVariable long productId) {
        invoice_productService.removeProductFromInvoice(invoiceId, productId);

        InvoiceProductResponse response = new InvoiceProductResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Product removed from invoice");

        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(InvoiceProductNotFoundException.class)
    public ResponseEntity<InvoiceProductResponse> handleInvoiceProductNotFoundException(InvoiceProductNotFoundException ex){
        InvoiceProductResponse response = new InvoiceProductResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DuplicateProductException.class)
    public ResponseEntity<InvoiceProductResponse> handleDuplicateProductException(DuplicateProductException ex){
        InvoiceProductResponse response = new InvoiceProductResponse();
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }



    }
