package za.co.douglasmedia.invoice_payment.Utils;

public class InvoicePaymentNotFoundException extends RuntimeException{
    public InvoicePaymentNotFoundException() {
    }

    public InvoicePaymentNotFoundException(String message) {
        super(message);
    }
}
