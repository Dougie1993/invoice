package za.co.douglasmedia.product.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.douglasmedia.product.Entities.Product;
import za.co.douglasmedia.product.Services.ProductService;
import za.co.douglasmedia.product.Utils.DuplicateNameException;
import za.co.douglasmedia.product.Utils.ProductFieldsNotCompleteException;
import za.co.douglasmedia.product.Utils.ProductNotFoundException;
import za.co.douglasmedia.product.Utils.ProductResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{userId}")
    public ResponseEntity<Optional<List<Product>>> getProducts(@PathVariable("userId") String userId) {
        Optional<List<Product>> products;
        products = productService.getProducts(userId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("product/name/{userId}/{name}")
    public ResponseEntity<Optional<Product>> getProductByName(@PathVariable("userId") String userId,
                                                              @PathVariable("name") String name) {
        Optional<Product> product;
        product = productService.getProductByName(userId, name);
        return ResponseEntity.ok(product);
    }

    @GetMapping("product/id/{userId}/{productId}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable("userId") String userId,
                                                            @PathVariable("productId") long productId) {
        Optional<Product> product;
        product = productService.getProductById(userId, productId);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/product/{userId}")
    public ResponseEntity<ProductResponse> addProduct(@PathVariable("userId") String userId,
                                                      @RequestBody Product product) {
        productService.addProduct(userId, product);

        ProductResponse response = new ProductResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Product Saved");

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/product/{userId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("userId") String userId,
                                                         @RequestBody Product product) {
        productService.updateProduct(userId, product);

        ProductResponse response = new ProductResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Product Updated");

        return ResponseEntity.ok(response);
    }

    // Exception handlers to format response

    @ExceptionHandler(ProductNotFoundException.class)
    public  ResponseEntity<ProductResponse> handleProductNotFoundException(ProductNotFoundException ex) {
        ProductResponse response = new ProductResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DuplicateNameException.class)
    public ResponseEntity<ProductResponse> handleDuplicateNameException(DuplicateNameException ex) {
        ProductResponse response = new ProductResponse();
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(ProductFieldsNotCompleteException.class)
    public  ResponseEntity<ProductResponse> handleProductFieldsNotCompleteException(ProductFieldsNotCompleteException ex) {
        ProductResponse response = new ProductResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
