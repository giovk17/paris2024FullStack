package com.example.paris2024Back.repositories;

import com.example.paris2024Back.domains.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepo extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByMatchIdAndUserId(Long matchId, Long userId);
}
