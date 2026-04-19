package com.prac.ecom_project.repo;
import com.prac.ecom_project.model.Order;
import com.prac.ecom_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Long> {
    List<Order> findByUser(User user);
    List<Order> findByStatus(String status);
}
