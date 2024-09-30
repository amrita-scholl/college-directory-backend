package com.example.collegedirectorybackend.service;

import com.example.collegedirectorybackend.model.User;
import com.example.collegedirectorybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // For password encryption

        public User authenticate(String username, String password) {
        System.out.println("in authenticate method");
        //return userRepository.findByUsername(username);
        User user = userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;

    }
}
