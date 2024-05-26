package com.example.paris2024Back.services;

import com.example.paris2024Back.domains.User;
import com.example.paris2024Back.dtos.UserDTO;
import com.example.paris2024Back.mappers.UserMapper;
import com.example.paris2024Back.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepo;
    private UserMapper userMapper;

    public UserDTO findById(Long userId){
       return this.userMapper.toDto(this.userRepo.findById(userId).orElseThrow(() -> new NullPointerException("User with id " + userId + " not found")));
    }

    public List<UserDTO> findAllUsers(){
        return this.userMapper.toDto(this.userRepo.findAll());
    }

    public void createUser(UserDTO userDTO){
        User user = this.userMapper.toUser(userDTO);
        this.userRepo.save(user);
    }

    public void deleteById(Long id){
        this.userRepo.deleteById(id);
    }
}
