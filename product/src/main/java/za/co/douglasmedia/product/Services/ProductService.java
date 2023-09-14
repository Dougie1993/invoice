package za.co.douglasmedia.product.Services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.douglasmedia.product.Entities.Product;
import za.co.douglasmedia.product.Repositories.ProductRepository;
import za.co.douglasmedia.product.Utils.DuplicateNameException;
import za.co.douglasmedia.product.Utils.ProductFieldsNotCompleteException;
import za.co.douglasmedia.product.Utils.ProductNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Add a product (Product names have to be unique)
    public void addProduct(String userId, Product product) {
        Optional<Product> productByName = productRepository.findProductByNameAndNotDeleted(userId, product.getName());
        if(productByName.isPresent()) {
            throw new DuplicateNameException("Product with name " + product.getName() + " already exists");
        } else {
            productRepository.save(product);
        }
    }

    // Get all products
    public Optional<List<Product>> getProducts(String userId) {
        Optional<List<Product>> products = productRepository.findAllProductsNotDeleted(userId);
        if(products.isEmpty() || products.get().isEmpty()) {
            throw new ProductNotFoundException("No products saved");
        } else {
            return products;
        }

    }

    // get product by name
    public Optional<Product> getProductByName(String userId, String name) {
        Optional<Product> productByName = productRepository.findProductByNameAndNotDeleted(userId, name);
        if(productByName.isEmpty()) {
            throw new ProductNotFoundException("Product with name " + name + " does not exist");
        } else {
            return productByName;
        }
    }

    // get product by productID
    public Optional<Product> getProductById(String userId, long productId) {
        Optional<Product> productById = productRepository.findProductByIdAndNotDeleted(userId, productId);
        if(productById.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + productId + " does not exist");
        } else {
            return productById;
        }
    }

    // Update product
    @Transactional // This is used when a modified query is in place and allows the update operation to be successful
    public void updateProduct(String userId, Product product) {
        // product name cannot be updated as we are using this as unique identifier.
        // Client will have to delete and then create a new product
        if (validateProduct(product)){
            Optional<Product> updateProduct = getProductByName(userId, product.getName());
            if (updateProduct.isPresent()){
                productRepository.updateProductByName(
                        product.getDescription(),
                        product.getPrice(),
                        product.getCategory(),
                        product.isDeleted(),
                        product.getUserId(),
                        product.getName()
                );
            } else {
                throw new ProductNotFoundException("Product with the name " + product.getName() + " does not exist");
            }
        } else {
            throw new ProductFieldsNotCompleteException("Product fields are not complete make sure all fields are filled in");
        }
    }

    // helper Methods
    private boolean validateProduct(Product product) {
        return (product.getName() != null) &&
                (product.getCategory() != null) &&
                (product.getDescription() != null);
        // left out is deleted as this method will be used when deleting a product

    }
}
