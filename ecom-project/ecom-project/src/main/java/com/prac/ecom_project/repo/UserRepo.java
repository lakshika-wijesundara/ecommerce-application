package com.prac.ecom_project.repo;
import com.prac.ecom_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
