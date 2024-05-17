package com.example.paris2024Back.repositories;

import com.example.paris2024Back.domains.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
