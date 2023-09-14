package za.co.douglasmedia.product.Utils;

public class DuplicateNameException extends RuntimeException{
    public DuplicateNameException() {
    }

    public DuplicateNameException(String message) {
        super(message);
    }
}
