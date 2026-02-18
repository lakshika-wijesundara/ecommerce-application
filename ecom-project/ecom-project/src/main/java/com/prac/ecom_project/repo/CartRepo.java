package com.prac.ecom_project.repo;

import com.prac.ecom_project.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long userId);
}
