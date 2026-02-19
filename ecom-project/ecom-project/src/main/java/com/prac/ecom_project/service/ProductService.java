package com.prac.ecom_project.service;

import com.prac.ecom_project.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product updateProduct(Long id,Product product);

    Product deleteProduct(Long id);

    List<Product> searchProducts(String keyword);
}
