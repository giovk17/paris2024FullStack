package com.example.paris2024Back.services;

import com.example.paris2024Back.domains.Match;
import com.example.paris2024Back.domains.Ticket;
import com.example.paris2024Back.domains.User;
import com.example.paris2024Back.dtos.TicketDTO;
import com.example.paris2024Back.mappers.TicketMapper;
import com.example.paris2024Back.repositories.MatchRepository;
import com.example.paris2024Back.repositories.TicketRepo;
import com.example.paris2024Back.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TicketService {

    private final TicketRepo ticketRepo;
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;
    private final TicketMapper ticketMapper;

    public TicketService(TicketRepo ticketRepo, MatchRepository matchRepository, UserRepository userRepository, TicketMapper ticketMapper) {
        this.ticketRepo = ticketRepo;
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
        this.ticketMapper = ticketMapper;
    }

    @Autowired


    public List<TicketDTO> getAllTickets(){
        return this.ticketMapper.toDto(this.ticketRepo.findAll());
    }

    public TicketDTO findByMatchIdAndUserId(Long matchId, Long userId){
        TicketDTO selected =  this.ticketMapper.toDto(this.ticketRepo.findByMatchIdAndUserId(matchId, userId));
        try {
            return selected;
        }catch (NullPointerException e){
            throw new NullPointerException("Ticket not found");
        }
    }

    public List<TicketDTO> findAllByMatchIdAndUserId(Long matchId, Long userId){
        List<TicketDTO> selected = this.ticketMapper.toDto(this.ticketRepo.findAllByMatchIdAndUserId(matchId, userId));
        try {
            return selected;
        }catch (NullPointerException e){
            throw new NullPointerException("Tickets not found");
        }
    }

    public void createTicket(TicketDTO ticketDTO){
        User user = this.userRepository.findById(ticketDTO.getUser().getId()).orElseThrow(() -> new NullPointerException("User not found") );
        Match match = this.matchRepository.findById(ticketDTO.getMatch().getId()).orElseThrow(() -> new NullPointerException("Match not found"));
        Ticket ticket = this.ticketMapper.toTicket(ticketDTO);

            this.ticketRepo.save(ticket);
            user.purchaseTicket(ticket);
            match.sellTicket(ticket);


    }
}
