package com.smartlocker.smartlockerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartlocker.smartlockerbackend.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
