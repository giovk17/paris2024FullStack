package com.example.paris2024Back.services;

import com.example.paris2024Back.mappers.UserMapper;
import com.example.paris2024Back.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepo;
    private UserMapper userMapper;

    public UserService(UserRepository userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }
}
