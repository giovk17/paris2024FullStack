package com.example.paris2024Back.repositories;

import com.example.paris2024Back.domains.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket, Long> {

    Ticket findByMatchIdAndUserId(Long matchId, Long userId);
    List<Ticket> findAllByMatchIdAndUserId(Long matchId, Long userId);
}
