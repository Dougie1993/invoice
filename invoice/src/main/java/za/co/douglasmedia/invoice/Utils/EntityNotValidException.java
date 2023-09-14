package za.co.douglasmedia.invoice.Utils;

public class EntityNotValidException extends RuntimeException{
    public EntityNotValidException() {
    }

    public EntityNotValidException(String message) {
        super(message);
    }
}
