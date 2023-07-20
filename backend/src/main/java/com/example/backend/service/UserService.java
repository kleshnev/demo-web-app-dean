package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String username, String pass) throws Exception {
        // Check if the user with the given email already exists
        if (userRepository.findByUserName(username).isPresent()) {
            throw new Exception("User with this email already exists.");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(pass);
        
        return userRepository.save(user);
    }
}