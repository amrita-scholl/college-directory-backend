package com.example.collegedirectorybackend.controller;

import com.example.collegedirectorybackend.model.User;
import com.example.collegedirectorybackend.repository.UserRepository;
import com.example.collegedirectorybackend.service.AuthService;
import com.example.collegedirectorybackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        boolean isAuthenticated = authService.authenticate(username, password);
        if (isAuthenticated) {
            return "Login successful!";
        } else {
            return "Invalid username or password.";
        }
    }

    @GetMapping("/login-user")
    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("login-user");
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return "Current logged-in user: " + userDetails.getUsername();
        }
        return "No user is currently authenticated.";
    }


}
