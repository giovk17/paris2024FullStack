package com.example.paris2024Back.services;


import com.example.paris2024Back.mappers.MatchMapper;
import com.example.paris2024Back.repositories.MatchRepository;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
    private MatchRepository matchRepo;
    private MatchMapper matchMapper;

    public MatchService(MatchRepository matchRepo, MatchMapper matchMapper) {
        this.matchRepo = matchRepo;
        this.matchMapper = matchMapper;
    }

}
