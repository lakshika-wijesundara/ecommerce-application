package com.prac.ecom_project.service.impl;

import com.prac.ecom_project.exceptions.DuplicateResourceException;
import com.prac.ecom_project.exceptions.ResourceNotFoundException;
import com.prac.ecom_project.model.User;
import com.prac.ecom_project.repo.UserRepo;
import com.prac.ecom_project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepository;
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        log.info("Creating user with email {}", user.getEmail());

        if (userRepository.findByEmail(user.getEmail()) != null) {
            log.warn("User already exists with email: {}", user.getEmail());
            throw new DuplicateResourceException(
                    "User already exists with email: " + user.getEmail()
            );
        }

        User newUser = userRepository.save(user);
        log.info("New user saved with id {}", newUser.getId());
        return newUser;
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Fetching all users");
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            log.warn("No users found");
        }

        log.info("Total users found: {}", users.size());
        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        log.info("Fetching user with id {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with id {}", id);
                    return new ResourceNotFoundException("User not found with id " + id);
                });
    }

    @Override
    public User updateUserById(Long id, User updatedUser) {
        log.info("Updating user with id {}", id);

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with id {}", id);
                    return new ResourceNotFoundException("User not found with id " + id);
                });

        // check if the new email is already taken by someone else
        User emailOwner = userRepository.findByEmail(updatedUser.getEmail());
        if (emailOwner != null && !emailOwner.getId().equals(id)) {
            log.warn("Another user already exists with email: {}", updatedUser.getEmail());
            throw new DuplicateResourceException(
                    "Another user already exists with email: " + updatedUser.getEmail()
            );
        }

        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setusername(updatedUser.getUsername());
        existingUser.setFirstname(updatedUser.getFirstname());
        existingUser.setLastname(updatedUser.getLastname());

        User saved = userRepository.save(existingUser);
        log.info("User updated successfully with id {}", id);
        return saved;
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("Deleting user with id {}", id);

        userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with id {}", id);
                    return new ResourceNotFoundException("User not found with id " + id);
                });

        userRepository.deleteById(id);
        log.info("User deleted successfully with id {}", id);
    }
}