package za.co.douglasmedia.invoice_system.Utils;

public class CustomerResponse {
    private int status;
    private String message;

    public CustomerResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
