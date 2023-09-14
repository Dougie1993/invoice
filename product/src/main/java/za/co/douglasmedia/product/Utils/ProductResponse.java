package za.co.douglasmedia.product.Utils;

public class ProductResponse {
    private int status;
    private String message;

    public ProductResponse() {
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
