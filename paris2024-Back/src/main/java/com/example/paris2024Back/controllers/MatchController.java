package com.example.paris2024Back.controllers;

import com.example.paris2024Back.dtos.MatchDTO;
import com.example.paris2024Back.enums.Sports;
import com.example.paris2024Back.services.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200" )
@RequestMapping(path = "/match")
public class MatchController {

    private final MatchService matchService;


    @GetMapping(produces = "application/json")
    public List<MatchDTO> findAllMatches(){
        return this.matchService.findAllMatches();
    }

    @GetMapping(produces = "application/json", path = "/{id}")
    public MatchDTO findMatchById(@PathVariable("id") Long id){
        return this.matchService.findById(id);
    }

    @GetMapping(produces = "application/json", path = "/sports/{sportName}")
    public List<MatchDTO> findBySportName(@PathVariable("sportName") String sportName) {
        Sports sport;
        try {
            sport = Sports.fromString(sportName);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid sport name: " + sportName);
        }
        return this.matchService.findBySportName(sport);
    }

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    public void createMatch(@RequestBody MatchDTO matchDTO){
        this.matchService.createMatch(matchDTO);
    }

    @DeleteMapping(path = "/delete/{id}", consumes = "application/json")
    public void deleteMatch(@PathVariable("id") Long id){
        this.matchService.deleteById(id);
    }

}
