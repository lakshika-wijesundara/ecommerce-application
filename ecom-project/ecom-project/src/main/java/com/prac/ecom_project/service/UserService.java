package com.prac.ecom_project.service;

import com.prac.ecom_project.model.Product;
import com.prac.ecom_project.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> getAllUsers();

    User findUserById(Long id);

    User updateUserById(Long id, User user);

    User deleteUserById(Long id, User user);

}
