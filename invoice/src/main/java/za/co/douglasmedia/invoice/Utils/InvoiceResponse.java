package za.co.douglasmedia.invoice.Utils;

public class InvoiceResponse {
    private int status;
    private String message;

    public InvoiceResponse() {
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
