package com.example.paris2024Back.controllers;

import com.example.paris2024Back.dtos.UserDTO;
import com.example.paris2024Back.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200" )
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;


    @GetMapping(produces = "application/json")
    public List<UserDTO> getAllUsers(){
        return this.userService.findAllUsers();
    }
}
