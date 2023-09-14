package za.co.douglasmedia.product.Entities;

import jakarta.persistence.*;

@Entity // maps this class to our database
@Table
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
            // how the id and sequence are generated
    )

    private long productId;
    private String name,description, userId;
    private double price;
    private boolean deleted;

    @Enumerated(EnumType.STRING)
    private Category category;

    // Product can be of type buy or sell only so using an enum to make sure of this
    public enum Category {
        BUY,
        SELL
    }

    public Product() {
    }

    public Product(String name, String description, String userId, double price, boolean deleted, Category category) {
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.price = price;
        this.deleted = deleted;
        this.category = category;
    }

    public Product(long productId, String name, String description, String userId, double price, boolean deleted, Category category) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.price = price;
        this.deleted = deleted;
        this.category = category;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", userId='" + userId + '\'' +
                ", price=" + price +
                ", deleted=" + deleted +
                ", category=" + category +
                '}';
    }
}
