package com.example.paris2024Back.services;


import com.example.paris2024Back.domains.Match;
import com.example.paris2024Back.dtos.MatchDTO;
import com.example.paris2024Back.enums.Sports;
import com.example.paris2024Back.mappers.MatchMapper;
import com.example.paris2024Back.repositories.MatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MatchService {
    private MatchRepository matchRepo;
    private MatchMapper matchMapper;


    public MatchDTO findById(Long matchId){
        return this.matchMapper.toDto(this.matchRepo.findById(matchId).orElseThrow(()-> new NullPointerException("Match with id " + matchId + " not found")));
    }

    public List<MatchDTO> findAllMatches(){
        return this.matchMapper.toDto(this.matchRepo.findAll());
    }

    public List<MatchDTO> findBySportName(Sports sportName){
        return this.matchMapper.toDto(this.matchRepo.findBySportName(sportName));
    }

    public void createMatch(MatchDTO matchDTO){
        Match match = this.matchMapper.toMatch(matchDTO);
        this.matchRepo.save(match);
    }

}
