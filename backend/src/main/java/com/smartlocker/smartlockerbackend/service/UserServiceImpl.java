package com.smartlocker.smartlockerbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smartlocker.smartlockerbackend.model.User;
import com.smartlocker.smartlockerbackend.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{   
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
