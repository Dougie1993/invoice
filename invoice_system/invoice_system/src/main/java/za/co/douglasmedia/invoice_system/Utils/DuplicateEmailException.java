package za.co.douglasmedia.invoice_system.Utils;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException() {
    }

    public DuplicateEmailException(String message) {
        super(message);
    }
}
