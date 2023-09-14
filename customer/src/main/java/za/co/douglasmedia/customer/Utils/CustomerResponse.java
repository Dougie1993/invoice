package za.co.douglasmedia.customer.Utils;


import za.co.douglasmedia.customer.Entities.Customer;

public class CustomerResponse {
    private int status;
    private String message;

    private Long custId;
    private String name, firstname, lastname, email, phone, website, Address;
    private boolean deleted;

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

    public void setCustomer(Customer customer) {
        this.custId = customer.getCustomerId();
        this.name = customer.getName();
        this.firstname = customer.getFirstname();
        this.lastname = customer.getLastname();
        this.email = customer.getEmail();
        this.Address = customer.getAddress();
        this.website = customer.getWebsite();
        this.deleted = customer.isDeleted();
        this.phone = customer.getPhone();
    }
}
