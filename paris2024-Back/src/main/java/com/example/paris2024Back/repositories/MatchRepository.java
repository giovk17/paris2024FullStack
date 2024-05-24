package com.example.paris2024Back.repositories;

import com.example.paris2024Back.domains.Match;
import com.example.paris2024Back.enums.Sports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findBySportName(Sports sportName);
}
