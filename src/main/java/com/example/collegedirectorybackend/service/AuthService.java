package com.example.collegedirectorybackend.service;

import com.example.collegedirectorybackend.model.User;
import com.example.collegedirectorybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticate(String username, String rawPassword) {
        // Fetch user from the repository
        User user = userRepository.findByUsername(username);

        if (user == null) {
            // Handle user not found scenario
            return false;
        }

        // Match the raw password with the stored hashed password
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
}

