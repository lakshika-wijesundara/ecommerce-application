package com.prac.ecom_project.service.impl;

import com.prac.ecom_project.exceptions.DuplicateResourceException;
import com.prac.ecom_project.exceptions.ResourceNotFoundException;
import com.prac.ecom_project.model.Product;
import com.prac.ecom_project.repo.ProductRepo;
import com.prac.ecom_project.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    // Logger - to track what's happening in your app
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepo productRepository;

    public ProductServiceImpl(ProductRepo productRepository) {

        this.productRepository = productRepository;
    }

    // CREATE
    @Override
    public Product createProduct(Product product) {
        log.info("Creating product with name: {}", product.getName());

        if (productRepository.existsByName(product.getName())) {
            log.warn("Product already exists with name: {}", product.getName());
            throw new DuplicateResourceException(
                    "Product already exists with name: " + product.getName()
            );
        }


        Product savedProduct = productRepository.save(product);
        log.info("Product created successfully with id: {}", savedProduct.getId());
        return savedProduct;
    }

    //  READ ALL
    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        log.info("Fetching all products");
        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            log.warn("No products found");
        }

        log.info("Total products found: {}", products.size());
        return products;
    }

    // READ BY ID
    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        log.info("Fetching product with id: {}", id);

        return productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with id: {}", id);
                    return new ResourceNotFoundException(
                            "Product not found with id: "+  id
                    );
                });
    }

    //  UPDATE
    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        log.info("Updating product with id: {}", id);

        // Fetch existing — throws if not found
        Product existingProduct = getProductById(id);

        // Check if new name conflicts with another product
        if (!existingProduct.getName().equals(updatedProduct.getName()) &&
                productRepository.existsByName(updatedProduct.getName())) {
            log.warn("Another product already exists with name: {}", updatedProduct.getName());
            throw new DuplicateResourceException(
                    "Another product already exists with name: " + updatedProduct.getName()
            );
        }

        // Update fields
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStock(updatedProduct.getStock());


        Product saved = productRepository.save(existingProduct);
        log.info("Product updated successfully with id: {}", saved.getId());
        return saved;
    }

    //  DELETE
    @Override
    public Product deleteProduct(Long id) {
        log.info("Deleting product with id: {}", id);

        // Fetch product — throws if not found
        Product product = getProductById(id);

        productRepository.delete(product);
        log.info("Product deleted successfully with id: {}", id);
        return product;
    }

    //  SEARCH
    @Override
    @Transactional(readOnly = true)
    public List<Product> searchProducts(String keyword) {
        log.info("Searching products with keyword: {}", keyword);

        if (keyword == null || keyword.trim().isEmpty()) {
            log.warn("Search keyword is empty, returning all products");
            return productRepository.findAll();
        }

        List<Product> results = productRepository.findByNameContainingIgnoreCase(keyword);
        log.info("Search results found: {}", results.size());
        return results;
    }
}