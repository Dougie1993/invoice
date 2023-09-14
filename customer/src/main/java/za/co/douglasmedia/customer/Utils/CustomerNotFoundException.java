package za.co.douglasmedia.customer.Utils;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException() {
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
