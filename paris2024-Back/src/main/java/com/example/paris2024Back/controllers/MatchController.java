package com.example.paris2024Back.controllers;

import com.example.paris2024Back.dtos.MatchDTO;
import com.example.paris2024Back.enums.Sports;
import com.example.paris2024Back.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping(produces = "application/json", path = "/{sportName}")
    public List<MatchDTO> findBySportName(@PathVariable("sportName") String sportName) {
        Sports sport;
        try {
            sport = Sports.fromString(sportName);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid sport name: " + sportName);
        }
        return this.matchService.findBySportName(sport);
    }

}
