package com.prac.ecom_project.repo;
import com.prac.ecom_project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Long> {
    List<Product> findByNameContainingIgnoreCase(String keyword);
}
