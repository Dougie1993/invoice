package za.co.douglasmedia.invoice.Services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.douglasmedia.invoice.Entities.Invoice;
import za.co.douglasmedia.invoice.InvoiceApplication;
import za.co.douglasmedia.invoice.Repositories.InvoiceRepository;
import za.co.douglasmedia.invoice.Utils.EntityNotValidException;
import za.co.douglasmedia.invoice.Utils.InvoiceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    // Add invoice
    public void addInvoice(Invoice invoice) {
        if(validateInvoice(invoice)) {
            invoiceRepository.save(invoice);
        } else {
            throw new EntityNotValidException("Please ensure all invoice fields are filled correctly");
        }
    }

    // Get invoices belonging to a customer
    public Optional<List<Invoice>> getCustomerInvoices(String userId, long customerId) {
        Optional<List<Invoice>> invoices = invoiceRepository.findAllInvoicesForCustomerNotDeleted(userId, customerId);
        if(invoices.isEmpty() || invoices.get().isEmpty()){
            throw new InvoiceNotFoundException("No Invoices found");
        } else {
            return invoices;
        }
    }

    // Get invoice by invoiceId
    public Optional<Invoice> getInvoiceById(String userId, long invoiceId) {
        Optional<Invoice> invoiceById = invoiceRepository.findInvoiceByIdAndNotDeleted(userId, invoiceId);
        if(invoiceById.isEmpty()) {
            throw new InvoiceNotFoundException("Invoice Not Found");
        } else  {
            return invoiceById;
        }
    }

    // Get all invoices for user
    public Optional<List<Invoice>> getInvoices(String userId) {
        Optional<List<Invoice>> invoices = invoiceRepository.findAllInvoicesForUserNotDeleted(userId);
        if(invoices.isEmpty() || invoices.get().isEmpty()) {
            throw new InvoiceNotFoundException("Invoice Not Found");
        } else  {
            return invoices;
        }
    }

    // Update invoice
    @Transactional
    public void updateInvoice(String userId, Invoice invoice) {
        //InvoiceId, Date Created and CustomerId cannot be updated
        Optional<Invoice> updateInvoice = getInvoiceById(userId, invoice.getInvoiceId());
        if(updateInvoice.isPresent()){
            invoiceRepository.updateInvoiceById(
                    invoice.getAmountDue(),
                    invoice.getStatus(),
                    invoice.getDueDate(),
                    invoice.getTotalCost(),
                    invoice.getAmountPaid(),
                    invoice.isDeleted(),
                    invoice.getUserId(),
                    invoice.getInvoiceId()
            );
        } else {
            throw new InvoiceNotFoundException("Invoice " + invoice.getInvoiceId() + " does not exist");
        }

    }

    // get invoices with customer

    // Helper Methods
    private boolean validateInvoice(Invoice invoice) {
      // return invoice.getCustomerId() != 0 &&
      //          invoice.getDateCreated() != null &&
       //         invoice.getDueDate() != null &&
       //         invoice.getStatus() != null;
        return true;
    }
}
