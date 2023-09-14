package za.co.douglasmedia.invoice_product.Utils;

public class DuplicateProductException extends RuntimeException{
    public DuplicateProductException() {
    }

    public DuplicateProductException(String message) {
        super(message);
    }
}
