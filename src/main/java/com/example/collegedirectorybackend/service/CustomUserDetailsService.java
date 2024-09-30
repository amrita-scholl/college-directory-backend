package com.example.collegedirectorybackend.service;

import com.example.collegedirectorybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;


import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder; // Injecting PasswordEncoder

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the repository
        com.example.collegedirectorybackend.model.User user = userRepository.findByUsername(username);
        System.out.println("Loading user with username: " + username);

        // If user is null, throw UsernameNotFoundException
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Return user details (You might need to convert `User` to `UserDetails`)
        return new User(
                user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                new ArrayList<>()  // Adjust authorities/roles as per your requirements
        );
    }

    // Method to match passwords
    public boolean matchPassword(String rawPassword, String storedPassword) {
        return passwordEncoder.matches(rawPassword, storedPassword);
    }
}