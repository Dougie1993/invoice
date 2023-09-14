package za.co.douglasmedia.customer.Utils;


public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException() {
    }

    public DuplicateEmailException(String message) {
        super(message);
    }
}
