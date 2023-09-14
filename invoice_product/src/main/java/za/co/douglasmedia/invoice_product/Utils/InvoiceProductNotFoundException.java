package za.co.douglasmedia.invoice_product.Utils;

public class InvoiceProductNotFoundException extends RuntimeException{
    public InvoiceProductNotFoundException() {
    }

    public InvoiceProductNotFoundException(String message) {
        super(message);
    }

}
