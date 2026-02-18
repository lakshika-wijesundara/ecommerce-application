package com.prac.ecom_project.repo;
import com.prac.ecom_project.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Long> {
    List<Order> findByUserId (Long userId);

}
