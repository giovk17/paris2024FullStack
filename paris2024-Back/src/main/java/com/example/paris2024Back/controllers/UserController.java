package com.example.paris2024Back.controllers;

import com.example.paris2024Back.dtos.UserDTO;
import com.example.paris2024Back.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/{id}", produces = "application/json")
    public UserDTO getById(@PathVariable("id") long id){
        return this.userService.findById(id);
    }

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    public void createUser(@RequestBody UserDTO userDTO){
        this.userService.createUser(userDTO);
    }

    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    public void deleteById(@PathVariable("id") Long id){
            this.userService.deleteById(id);
        }
}
