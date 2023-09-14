package za.co.douglasmedia.invoice_product.Entities;

import jakarta.persistence.*;

@Entity // maps this class to our database
@Table
public class Invoice_Product {
    @Id
    @SequenceGenerator(
            name = "invoice_product_sequence",
            sequenceName = "invoice_product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "invoice_product_sequence"
            // how the id and sequence are generated
    )

    private long invoice_productId;
    private long invoiceId, productId;
    private double quantity;

    public Invoice_Product() {
    }

    public Invoice_Product(long invoiceId, long productId, double quantity) {
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Invoice_Product(long invoice_productId, long invoiceId, long productId, double quantity) {
        this.invoice_productId = invoice_productId;
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public long getInvoice_productId() {
        return invoice_productId;
    }

    public void setInvoice_productId(long invoice_productId) {
        this.invoice_productId = invoice_productId;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Invoice_Product{" +
                "invoice_productId=" + invoice_productId +
                ", invoiceId=" + invoiceId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
