package com.example.paris2024Back.services;


import com.example.paris2024Back.domains.Match;
import com.example.paris2024Back.dtos.MatchDTO;
import com.example.paris2024Back.mappers.MatchMapper;
import com.example.paris2024Back.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {
    private MatchRepository matchRepo;
    private MatchMapper matchMapper;

    @Autowired
    public MatchService(MatchRepository matchRepo, MatchMapper matchMapper) {
        this.matchRepo = matchRepo;
        this.matchMapper = matchMapper;
    }

    public MatchDTO findById(Long matchId){
        MatchDTO selected = this.matchMapper.toDto(this.matchRepo.findById(matchId).get());
        try {
            return selected;
        }catch (NullPointerException e){
            throw new NullPointerException("Match with id " + matchId + " not found");
        }
    }

    public List<MatchDTO> findAllMatches(){
        return this.matchMapper.toDto(this.matchRepo.findAll());
    }

    public void createMatch(MatchDTO matchDTO){
        Match match = this.matchMapper.toMatch(matchDTO);
        this.matchRepo.save(match);
    }

}
