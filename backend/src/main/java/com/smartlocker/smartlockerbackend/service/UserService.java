package com.smartlocker.smartlockerbackend.service;

import org.springframework.stereotype.Service;

import com.smartlocker.smartlockerbackend.model.User;
@Service
public interface UserService {
     User registerUser(User user);

}
