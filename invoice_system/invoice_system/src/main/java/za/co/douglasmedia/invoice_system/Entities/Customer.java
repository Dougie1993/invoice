package za.co.douglasmedia.invoice_system.Entities;

import jakarta.persistence.*;

@Entity // maps this class to our database
@Table
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
            // how the id and sequence are generated
    )
    private Long custId;
    private String name, firstname, lastname, email, phone, website, Address;
    private boolean deleted;

    public Customer(Long custId,
                    String name,
                    String firstname,
                    String lastname,
                    String email,
                    String phone,
                    String website,
                    String address,
                    boolean deleted) {
        this.custId = custId;
        this.name = name;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.website = website;
        Address = address;
        this.deleted = deleted;
    }

    // Database will generate id for us we need a constructor without id
    public Customer(String name,
                    String firstname,
                    String lastname,
                    String email,
                    String phone,
                    String website,
                    String address,
                    boolean deleted) {
        this.name = name;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.website = website;
        Address = address;
        this.deleted = deleted;
    }

    public Customer() {
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", Address='" + Address + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
