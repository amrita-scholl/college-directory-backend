package com.example.collegedirectorybackend.repository;

import java.util.Optional;

import com.example.collegedirectorybackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}