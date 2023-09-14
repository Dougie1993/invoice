package za.co.douglasmedia.invoice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import za.co.douglasmedia.invoice.Entities.Customer;
import za.co.douglasmedia.invoice.Entities.Invoice;
import za.co.douglasmedia.invoice.Entities.InvoiceList;
import za.co.douglasmedia.invoice.Services.InvoiceService;
import za.co.douglasmedia.invoice.Utils.CustomerNotFoundException;
import za.co.douglasmedia.invoice.Utils.EntityNotValidException;
import za.co.douglasmedia.invoice.Utils.InvoiceNotFoundException;
import za.co.douglasmedia.invoice.Utils.InvoiceResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;
    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // Get invoices for customer
    @GetMapping("customer/{userId}/{customerId}")
    public ResponseEntity<Optional<List<Invoice>>> getCustomerInvoices(@PathVariable("userId") String userId,
                                                                       @PathVariable("customerId") long customerId){
        Optional<List<Invoice>> invoices;
        invoices = invoiceService.getCustomerInvoices(userId, customerId);
        return ResponseEntity.ok(invoices);
    }

    // Get invoice by Id
    @GetMapping("invoice/{userId}/{invoiceId}")
    public ResponseEntity<Optional<Invoice>> getInvoiceById(@PathVariable("userId") String userId,
                                                            @PathVariable("invoiceId") long invoiceId){
        Optional<Invoice> invoice;
        invoice = invoiceService.getInvoiceById(userId, invoiceId);
        return ResponseEntity.ok(invoice);
    }

    // Add Invoice
    @PostMapping("/{userId}")
    public ResponseEntity<InvoiceResponse> addInvoice(@RequestBody Invoice invoice){
        invoiceService.addInvoice(invoice);
        InvoiceResponse response = new InvoiceResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Invoice Saved");

        return ResponseEntity.ok(response);

    }

    // Update Invoice
    @PutMapping("/invoice/{userId}")
    public ResponseEntity<InvoiceResponse> updateInvoice(@PathVariable("userId") String userId,
                                                         @RequestBody Invoice invoice){
        invoiceService.updateInvoice(userId, invoice);
        InvoiceResponse response = new InvoiceResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Invoice Updated");

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{userId}/invoiceList")
    public ResponseEntity<List<InvoiceList>> getUserInvoices(@PathVariable("userId") String userId){
        // Get all invoices for invoice dashboard
        Optional<List<Invoice>> invoicesOptional = invoiceService.getInvoices(userId);
        List<InvoiceList> invoiceList = new ArrayList<>();

        if (invoicesOptional.isPresent()) {
            List<Invoice> invoices = invoicesOptional.get();

            for (Invoice invoice : invoices) {
                // Fetch the customer details using the customerId
                System.out.println(invoice.toString());
                Customer customer = restTemplate.getForObject("http://localhost:8081/customer/customer/id/" + userId + "/" + invoice.getCustomerId(), Customer.class);

                // Create the InvoiceList object
                assert customer != null;
                InvoiceList invoiceListItem = new InvoiceList(
                        invoice.getStatus(),
                        invoice.getDateCreated(),
                        invoice.getDueDate(),
                        invoice.getInvoiceId(),
                        customer.getName(), // Use the customer name from the fetched customer object
                        invoice.getAmountDue(),
                        invoice.getTotalCost()
                );

                invoiceList.add(invoiceListItem);
            }
        }

        return new ResponseEntity<>(invoiceList, HttpStatus.OK);
    }



    // Exception handlers to format response
    @ExceptionHandler(InvoiceNotFoundException.class)
    public  ResponseEntity<InvoiceResponse> handleInvoiceNotFoundException(InvoiceNotFoundException ex) {
        InvoiceResponse response = new InvoiceResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(EntityNotValidException.class)
    public ResponseEntity<InvoiceResponse> handleEntityNotValidException(EntityNotValidException ex){
        InvoiceResponse response = new InvoiceResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    private ResponseEntity<InvoiceResponse> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        InvoiceResponse response = new InvoiceResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


}
