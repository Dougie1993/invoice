package za.co.douglasmedia.invoice_payment.Utils;

public class EntityNotValidException extends RuntimeException{
    public EntityNotValidException() {
    }

    public EntityNotValidException(String message) {
        super(message);
    }
}
