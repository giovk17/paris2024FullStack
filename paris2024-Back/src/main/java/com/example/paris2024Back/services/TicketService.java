package com.example.paris2024Back.services;

import com.example.paris2024Back.domains.Match;
import com.example.paris2024Back.domains.Ticket;
import com.example.paris2024Back.domains.User;
import com.example.paris2024Back.dtos.TicketDTO;
import com.example.paris2024Back.mappers.TicketMapper;
import com.example.paris2024Back.repositories.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TicketService {

    private final TicketRepo ticketRepo;
    private final TicketMapper ticketMapper;

    @Autowired
    public TicketService(TicketRepo ticketRepo, TicketMapper ticketMapper) {
        this.ticketRepo = ticketRepo;
        this.ticketMapper = ticketMapper;
    }

    public TicketDTO findByMatchIdAndUserId(Long matchId, Long userId){
        return this.ticketMapper.toDto(this.ticketRepo.findByMatchIdAndUserId(matchId, userId));
    }

    public List<TicketDTO> findAllByMatchIdAndUserId(Long matchId, Long userId){
        return this.ticketMapper.toDto(this.ticketRepo.findAllByMatchIdAndUserId(matchId, userId));
    }

    public void createTicket(TicketDTO ticketDTO){
        User user = ticketDTO.getUser();
        Match match = ticketDTO.getMatch();
        Ticket ticket = this.ticketMapper.toTicket(ticketDTO);
        user.purchaseTicket(ticket);
        match.sellTicket(ticket);
        this.ticketRepo.save(ticket);
    }
}
