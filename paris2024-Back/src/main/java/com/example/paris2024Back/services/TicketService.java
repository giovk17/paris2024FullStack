package com.example.paris2024Back.services;

import com.example.paris2024Back.domains.Ticket;
import com.example.paris2024Back.mappers.TicketMapper;
import com.example.paris2024Back.repositories.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TicketService {

    private final TicketRepo ticketRepo;
    private final TicketMapper ticketMapper;

    @Autowired
    public TicketService(TicketRepo ticketRepo, TicketMapper ticketMapper) {
        this.ticketRepo = ticketRepo;
        this.ticketMapper = ticketMapper;
    }

    public Optional<Ticket> findByMatchIdAndUserId(Long matchId, Long userId){
        return null;
    }
}
