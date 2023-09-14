package za.co.douglasmedia.invoice_payment.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.douglasmedia.invoice_payment.Entities.Invoice_Payment;
import za.co.douglasmedia.invoice_payment.Repositories.Invoice_PaymentRepository;
import za.co.douglasmedia.invoice_payment.Utils.EntityNotValidException;
import za.co.douglasmedia.invoice_payment.Utils.InvoicePaymentNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class Invoice_PaymentService {
    private final Invoice_PaymentRepository invoice_paymentRepository;

    @Autowired
    public Invoice_PaymentService(Invoice_PaymentRepository invoice_paymentRepository) {
        this.invoice_paymentRepository = invoice_paymentRepository;
    }

    public void addInvoicePayment(Invoice_Payment invoice_payment){
        if(validateInvoicePayment(invoice_payment)) {
            invoice_paymentRepository.save(invoice_payment);
        } else {
            throw new EntityNotValidException("Please ensure all invoice_payment fields are filled correctly");
        }
    }

    public Optional<List<Invoice_Payment>> getInvoicePayments(long invoiceId) {
        Optional<List<Invoice_Payment>> invoicePayments = invoice_paymentRepository.findAllPaymentsForInvoice(invoiceId);
        if (invoicePayments.isEmpty() || invoicePayments.get().isEmpty()){
            throw new InvoicePaymentNotFoundException("No payments found for this invoice");
        } else {
            return invoicePayments;
        }

    }

    // Helper methods
    private boolean validateInvoicePayment(Invoice_Payment invoice_payment) {
        return invoice_payment.getInvoiceId() != 0 &&
                invoice_payment.getAmount() != 0 &&
                invoice_payment.getDatePayed() != null &&
                invoice_payment.getNotes() != null &&
                invoice_payment.getMethod() != null;
    }


}
