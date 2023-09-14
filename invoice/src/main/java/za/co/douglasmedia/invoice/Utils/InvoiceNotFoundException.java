package za.co.douglasmedia.invoice.Utils;

public class InvoiceNotFoundException extends RuntimeException{
    public InvoiceNotFoundException() {
    }

    public InvoiceNotFoundException(String message) {
        super(message);
    }
}
