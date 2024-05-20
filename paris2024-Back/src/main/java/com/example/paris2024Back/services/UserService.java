package com.example.paris2024Back.services;

import com.example.paris2024Back.domains.User;
import com.example.paris2024Back.dtos.UserDTO;
import com.example.paris2024Back.mappers.UserMapper;
import com.example.paris2024Back.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepo;
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    public UserDTO findById(Long userId){
       UserDTO selected = this.userMapper.toDto(this.userRepo.findById(userId).get());
        try {
            return selected;
        }catch (NullPointerException e){
            throw new NullPointerException("User with id " + userId + " not found");
        }
    }

    public List<UserDTO> findAllUsers(){
        return this.userMapper.toDto(this.userRepo.findAll());
    }

    public void createUser(UserDTO userDTO){
        User user = this.userMapper.toUser(userDTO);
        this.userRepo.save(user);
    }
}
