package za.co.douglasmedia.invoice_payment.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity // maps this class to our database
@Table
public class Invoice_Payment {
    @Id
    @SequenceGenerator(
            name = "invoice_payment_sequence",
            sequenceName = "invoice_payment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "invoice_payment_sequence"
            // how the id and sequence are generated
    )


    private long invoice_payId;
    private long invoiceId;
    private double amount;
    private Date datePayed;
    private String notes;

    @Enumerated(EnumType.STRING)
    private paymentMethod method;
    public enum paymentMethod {
        card,
        cash,
        eft
    }

    public Invoice_Payment() {
    }

    public Invoice_Payment(long invoiceId, double amount, Date datePayed, String notes, paymentMethod method) {
        this.invoiceId = invoiceId;
        this.amount = amount;
        this.datePayed = datePayed;
        this.notes = notes;
        this.method = method;
    }

    public Invoice_Payment(long invoice_payId, long invoiceId, double amount, Date datePayed, String notes, paymentMethod method) {
        this.invoice_payId = invoice_payId;
        this.invoiceId = invoiceId;
        this.amount = amount;
        this.datePayed = datePayed;
        this.notes = notes;
        this.method = method;
    }

    public long getInvoice_payId() {
        return invoice_payId;
    }

    public void setInvoice_payId(long invoice_payId) {
        this.invoice_payId = invoice_payId;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDatePayed() {
        return datePayed;
    }

    public void setDatePayed(Date datePayed) {
        this.datePayed = datePayed;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public paymentMethod getMethod() {
        return method;
    }

    public void setMethod(paymentMethod method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "Invoice_Payment{" +
                "invoice_payId=" + invoice_payId +
                ", invoiceId=" + invoiceId +
                ", amount=" + amount +
                ", datePayed=" + datePayed +
                ", notes='" + notes + '\'' +
                ", method=" + method +
                '}';
    }
}
