package com.example.paris2024Back.mappers;

import com.example.paris2024Back.domains.User;
import com.example.paris2024Back.dtos.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public UserDTO toDto(User user){
        return new UserDTO(user.getId(), user.getName(), user.getUserName(), user.getPassword(), user.getUserRole(), user.getOwnedTickets());
    }

    public List<UserDTO> toDto(List<User> users){
        return users.stream().map(this::toDto).toList();
    }

    public User toUser(UserDTO userDTO){
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getUserName(), userDTO.getPassword(), userDTO.getUserRole(), userDTO.getOwnedTickets());
    }

    public List<User> toUser(List<UserDTO> userDTOS){
        return userDTOS.stream().map(this::toUser).toList();
    }
}
