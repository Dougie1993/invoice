package za.co.douglasmedia.invoice.Entities;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
// Data sent when invoices are requested from front end for the user
public class InvoiceList {
    @Enumerated(EnumType.STRING)
    private Invoice.Status status;
    private Invoice invoice;
    private Customer customer;



    // Invoice can only be in 1 of the 5 states at a time
    public enum Status {
        DRAFT,
        APPROVED,
        SENT,
        PAID,
        OVERDUE
    }
    @Temporal(TemporalType.DATE) // To format the date
    private Date dateCreated, dueDate;
    long invoiceId;
    String customerName;
    private float amountDue, totalCost;

    public InvoiceList() {
    }

    public InvoiceList(Invoice.Status status, Date dateCreated, Date dueDate, long invoiceId, String customerName, float amountDue, float totalCost) {
        this.status = status;
        this.dateCreated = dateCreated;
        this.dueDate = dueDate;
        this.invoiceId = invoiceId;
        this.customerName = customerName;
        this.amountDue = amountDue;
        this.totalCost = totalCost;
    }

    public InvoiceList(Invoice.Status status, Invoice invoice, Customer customer, Date dateCreated, Date dueDate, long invoiceId, String customerName, float amountDue, float totalCost) {
        this.status = status;
        this.invoice = invoice;
        this.customer = customer;
        this.dateCreated = dateCreated;
        this.dueDate = dueDate;
        this.invoiceId = invoiceId;
        this.customerName = customerName;
        this.amountDue = amountDue;
        this.totalCost = totalCost;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Invoice.Status getStatus() {
        return status;
    }

    public void setStatus(Invoice.Status status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public float getAmountDue() {
        return amountDue;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public void setAmountDue(float amountDue) {
        this.amountDue = amountDue;
    }

    @Override
    public String toString() {
        return "InvoiceList{" +
                "status=" + status +
                ", dateCreated=" + dateCreated +
                ", dueDate=" + dueDate +
                ", invoiceId=" + invoiceId +
                ", customerName='" + customerName + '\'' +
                ", amountDue=" + amountDue +
                '}';
    }
}
