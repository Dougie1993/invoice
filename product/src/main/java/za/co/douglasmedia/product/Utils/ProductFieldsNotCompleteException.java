package za.co.douglasmedia.product.Utils;

public class ProductFieldsNotCompleteException extends RuntimeException{
    public ProductFieldsNotCompleteException() {
    }

    public ProductFieldsNotCompleteException(String message) {
        super(message);
    }
}
