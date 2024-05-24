package com.example.paris2024Back.controllers;

import com.example.paris2024Back.dtos.MatchDTO;
import com.example.paris2024Back.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200" )
@RequestMapping(path = "/match")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping(produces = "application/json")
    public List<MatchDTO> findAllMatches(){
        return this.matchService.findAllMatches();
    }
}
