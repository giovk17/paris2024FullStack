package com.example.paris2024Back.services;


import com.example.paris2024Back.domains.Match;
import com.example.paris2024Back.dtos.MatchDTO;
import com.example.paris2024Back.mappers.MatchMapper;
import com.example.paris2024Back.repositories.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {
    private MatchRepository matchRepo;
    private MatchMapper matchMapper;

    public MatchService(MatchRepository matchRepo, MatchMapper matchMapper) {
        this.matchRepo = matchRepo;
        this.matchMapper = matchMapper;
    }

    public MatchDTO findById(Long matchId){
        return this.matchMapper.toDto(this.matchRepo.findById(matchId).get());
    }

    public List<MatchDTO> findAllUsers(){
        return this.matchMapper.toDto(this.matchRepo.findAll());
    }

    public void createMatch(MatchDTO matchDTO){
        Match match = this.matchMapper.toMatch(matchDTO);
        this.matchRepo.save(match);
    }

}
