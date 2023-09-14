package za.co.douglasmedia.invoice_payment.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.douglasmedia.invoice_payment.Entities.Invoice_Payment;
import za.co.douglasmedia.invoice_payment.Services.Invoice_PaymentService;
import za.co.douglasmedia.invoice_payment.Utils.EntityNotValidException;
import za.co.douglasmedia.invoice_payment.Utils.InvoicePaymentNotFoundException;
import za.co.douglasmedia.invoice_payment.Utils.Invoice_PaymentResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "invoice_payment")
public class Invoice_PaymentController {
    private final Invoice_PaymentService invoice_paymentService;

    @Autowired
    public Invoice_PaymentController(Invoice_PaymentService invoice_paymentService) {
        this.invoice_paymentService = invoice_paymentService;
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<Optional<List<Invoice_Payment>>> getInvoicePayments(@PathVariable("invoiceId") long invoiceId){
        Optional<List<Invoice_Payment>> invoicePayments = invoice_paymentService.getInvoicePayments(invoiceId);
        return ResponseEntity.ok(invoicePayments);
    }

    @PostMapping
    public ResponseEntity<Invoice_PaymentResponse> addInvoicePayment(@RequestBody Invoice_Payment invoice_payment){
        invoice_paymentService.addInvoicePayment(invoice_payment);

        Invoice_PaymentResponse response = new Invoice_PaymentResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Invoice Payment Saved");

        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(InvoicePaymentNotFoundException.class)
    public ResponseEntity<Invoice_PaymentResponse> handleInvoicePaymentNotFoundException(InvoicePaymentNotFoundException ex){
        Invoice_PaymentResponse response = new Invoice_PaymentResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(EntityNotValidException.class)
    public ResponseEntity<Invoice_PaymentResponse> handleEntityNotValidException(EntityNotValidException ex){
        Invoice_PaymentResponse response = new Invoice_PaymentResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
